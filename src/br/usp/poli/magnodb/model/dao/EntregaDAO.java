package br.usp.poli.magnodb.model.dao;

import br.usp.poli.magnodb.model.Cliente;
import br.usp.poli.magnodb.model.Entrega;
import br.usp.poli.magnodb.model.Fisico;
import br.usp.poli.magnodb.model.Juridico;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            statement.setDate(3, new Date(entrega.getDataDespache().getTime()));
            statement.setDate(4, new Date(entrega.getDataRecepcao().getTime()));

            statement.executeUpdate();

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
