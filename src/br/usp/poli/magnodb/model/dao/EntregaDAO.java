package br.usp.poli.magnodb.model.dao;

import br.usp.poli.magnodb.model.*;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;

/**
 * Created by mateus on 06/12/16.
 */
public class EntregaDAO extends DBConnector {
    private static EntregaDAO instance;

    public static void setUpDAO(DataSource dataSource) {
        instance = new EntregaDAO(dataSource);
    }

    private EntregaDAO(DataSource dataSource) {
        try {
            create(dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static EntregaDAO getInstance() {
        return instance;
    }

    public void cadastrarEntrega(Entrega entrega) {
        try {
            connect();
            Connection con = getConnection();

            PreparedStatement statement = con.prepareStatement("INSERT INTO Entrega " +
                    "(endereco, frete, data_despache, data_recepcao) " +
                    "VALUES (?, ?, ?, ?)");
            statement.setString(1, entrega.getEndereco());
            statement.setFloat(2, entrega.getFrete());
            statement.setDate(3, new java.sql.Date(entrega.getDataDespache().getTime()));
            statement.setDate(4, new java.sql.Date(entrega.getDataRecepcao().getTime()));

            statement.executeUpdate();

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Entrega buscarEntrega(int id) {
        Entrega entrega = null;

        try {
            connect();
            Connection con = getConnection();

            PreparedStatement statment = con.prepareStatement("SELECT * FROM Entrega WHERE  id = ?");
            statment.setInt(1, id);

            ResultSet rs = statment.executeQuery();
            if (rs.next()) {
                entrega = new Entrega(rs.getString("endereco"), rs.getFloat("frete"),
                          rs.getTimestamp("data_despache"),
                          rs.getTimestamp("data_recepcao"));
                entrega.setId(rs.getInt("id"));
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entrega;
    }
}
