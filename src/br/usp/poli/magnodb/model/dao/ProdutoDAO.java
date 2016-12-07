package br.usp.poli.magnodb.model.dao;

import br.usp.poli.magnodb.model.Produto;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by gustavo on 28/11/16.
 */
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
                    "VALUES (?, ?, ?)");
            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getDescricao());
            statement.setDate(3, new Date(produto.getCriacao().getTime()));

            statement.executeUpdate();

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

            PreparedStatement statment = con.prepareStatement("SELECT * FROM Produto WHERE  id = ?");
            statment.setInt(1, id);

            ResultSet rs = statment.executeQuery();
            if (rs.next()) {
                produto = new Produto(rs.getString("nome"), rs.getString("descricao"));
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

            PreparedStatement statment = con.prepareStatement("SELECT * FROM Produto WHERE  nome LIKE ?");
            statment.setString(1, nome);

            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto(rs.getString("nome"), rs.getString("descricao"));
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

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new LinkedList<>();

        try {
            connect();
            Connection con = getConnection();

            PreparedStatement statment = con.prepareStatement("SELECT * FROM Produto");

            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto(rs.getString("nome"), rs.getString("descricao"));
                produto.setId(rs.getInt("id"));

                produtos.add(produto);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }
}
