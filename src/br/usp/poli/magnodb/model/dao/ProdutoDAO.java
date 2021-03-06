package br.usp.poli.magnodb.model.dao;

import br.usp.poli.magnodb.model.Produto;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.time.Instant;
import java.util.*;

public class ProdutoDAO extends DBConnector {

    private static ProdutoDAO instance;

    public static void setUpDAO(DataSource dataSource) {
        instance = new ProdutoDAO(dataSource);
    }

    private ProdutoDAO(DataSource dataSource) {
        try {
            create(dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void cadastrarProduto(Produto produto) {
        try {
            connect();
            Connection con = getConnection();

            PreparedStatement statement = con.prepareStatement("INSERT INTO Produto " +
                    "(nome, descricao, data_criacao) " +
                    "VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getDescricao());
            statement.setTimestamp(3, new Timestamp(produto.getCriacao().getTime()));

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (!rs.next()) {
                con.close();
                throw new SQLException();
            }

            produto.setId(rs.getInt(1));

            atualizarPreco(produto.getPreco(), produto);

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Produto buscarProduto(int id) {
        Produto produto = null;

        try {
            connect();
            Connection con = getConnection();

            PreparedStatement statment = con.prepareStatement("SELECT Produto.*, preco FROM Produto, Preco " +
                    "WHERE  Produto.id = ? AND produto = Produto.id ORDER BY `data` DESC LIMIT 1");
            statment.setInt(1, id);

            ResultSet rs = statment.executeQuery();
            if (rs.next()) {
                produto = new Produto(rs.getString("nome"), rs.getString("descricao"), rs.getDate("data_criacao"), rs.getFloat("preco"));
                produto.setId(rs.getInt("id"));
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produto;
    }

    public Set<Produto> buscarProduto(String nome) {
        Set<Produto> produtos = new TreeSet<>();

        try {
            connect();
            Connection con = getConnection();

            String query = "SELECT P.*, preco FROM Produto P JOIN Preco ON (id = produto) " +
            "WHERE nome LIKE ? AND data=(SELECT MAX(data) FROM Preco WHERE produto = P.id)";

            PreparedStatement statment = con.prepareStatement(query);
            statment.setString(1, nome);

            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto(rs.getString("nome"), rs.getString("descricao"), rs.getDate("data_criacao"), rs.getFloat("preco"));
                produto.setId(rs.getInt("id"));

                produtos.add(produto);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public static ProdutoDAO getInstance() {
        return instance;
    }

    public List<Produto> listarTodosProdutos() {
        List<Produto> produtos = new LinkedList<>();
        try {
            connect();
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT Produto.*, preco FROM Produto " +
                    "INNER JOIN Preco ON Produto.id = Preco.produto");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto(rs.getString("nome"), rs.getString("descricao"),
                        rs.getDate("data_criacao"), rs.getFloat("preco"));
                produto.setId(rs.getInt("id"));

                produtos.add(produto);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new LinkedList<>();

        try {
            connect();
            Connection con = getConnection();

            String query = "SELECT P.*, preco, (SELECT SUM(I.quantidade) AS quantidade FROM Item I WHERE P.id = produto) AS quantidade, MAX(Pd.data) AS data " +
            "FROM Produto P " +
            "LEFT JOIN Preco Pr ON (P.id = produto) " +
            "LEFT JOIN Item I ON P.id = I.produto " +
            "LEFT JOIN Pedido Pd ON I.pedido = Pd.id " +
            "WHERE  Pr.data=(SELECT MAX(data) FROM Preco Pr WHERE Pr.produto = P.id) " +
            "GROUP BY P.id";

            System.out.println(query);

            PreparedStatement statment = con.prepareStatement(query);

            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto(rs.getString("nome"), rs.getString("descricao"), rs.getDate("data_criacao"), rs.getFloat("preco"));
                produto.setUltimaVenda(rs.getDate("data"));
                produto.setQuantidadeVendida(rs.getInt("quantidade"));
                produto.setId(rs.getInt("id"));

                produtos.add(produto);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public void atualizarPreco(Float preco, Produto produto) {
        String query = "INSERT INTO Preco (produto, preco, data) VALUES (?, ?, ?)";

        try {
            connect();
            Connection con = getConnection();

            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, produto.getId());
            stmt.setFloat(2, preco);
            stmt.setTimestamp(3, new Timestamp(Instant.now().toEpochMilli()));

            stmt.executeUpdate();

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HashMap<java.util.Date,Float> getPrecos(Produto produto) {
        String query = "SELECT preco, data FROM Preco WHERE produto = ?";
        HashMap<java.util.Date, Float> map = new HashMap<>();

        try {
            connect();
            Connection con = getConnection();

            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, produto.getId());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                java.util.Date data = rs.getDate("data");
                Float preco = rs.getFloat("preco");

                map.put(data, preco);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }
}
