package br.usp.poli.magnodb.model.dao;

import br.usp.poli.magnodb.model.*;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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

    public List<Entrega> listarEntregas() {
        List<Entrega> entregas = new LinkedList<>();

        try {
            connect();
            Connection con = getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT * FROM Entrega");

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Entrega entrega  = new Entrega(rs.getString("endereco"), rs.getFloat("frete"),
                        rs.getTimestamp("data_despache"), rs.getTimestamp("data_recepcao"));
                entrega .setId(rs.getInt("id"));

                entregas.add(entrega);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entregas ;
    }

    public float calcularTempoMedio() {
        float tempoMedio = -1;
        try {
            connect();
            Connection con = getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT AVG(DATEDIFF(data_recepcao,data_despache)) AS tempo_medio FROM Entrega");
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                tempoMedio = rs.getFloat("tempo_medio");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tempoMedio;
    }

    public int calcularNumeroEntregasRecebidas() {
        int numeroEntregasRecebidas = -1;
        try {
            connect();
            Connection con = getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) AS numeroEntregasRecebidas FROM Entrega WHERE data_recepcao < CURDATE()");
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                numeroEntregasRecebidas = rs.getInt("numeroEntregasRecebidas");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numeroEntregasRecebidas;
    }

    public float calcularMediaFrete() {
        float mediaFrete = -1;
        try {
            connect();
            Connection con = getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT AVG(frete) AS mediaFrete FROM Entrega ");
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                mediaFrete = rs.getInt("mediaFrete");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mediaFrete;
    }
}
