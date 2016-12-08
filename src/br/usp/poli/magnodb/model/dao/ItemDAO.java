package br.usp.poli.magnodb.model.dao;

import br.usp.poli.magnodb.model.Context;
import br.usp.poli.magnodb.model.Item;
import br.usp.poli.magnodb.model.Produto;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by mateus on 08/12/16.
 */
public class ItemDAO extends DBConnector {
    private static ItemDAO instance;

    public static void setUpDAO(DataSource dataSource) {
        instance = new ItemDAO(dataSource);
    }

    private ItemDAO(DataSource dataSource) {
        try {
            create(dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static ItemDAO getInstance() {
        return instance;
    }

    public void cadastrarItem(Item item) {
            try {
                connect();
                Connection con = getConnection();

                PreparedStatement statement = con.prepareStatement("INSERT INTO Item " +
                        "(produto, pedido, id, quantidade, lote) " +
                        "VALUES (?, ?, ?, ?, ?)");
                statement.setInt(1, item.getProduto().getId());
                statement.setInt(2, item.getPedido().getId());
                statement.setInt(3, 0);
                statement.setFloat(4, item.getQuantidade());
                statement.setString(5, item.getLote());

                statement.executeUpdate();

                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }



    }
}
