package br.usp.poli.magnodb.model.dao;

import br.usp.poli.magnodb.model.Produto;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ProdutoDAO getInstance() {
        return instance;
    }

}
