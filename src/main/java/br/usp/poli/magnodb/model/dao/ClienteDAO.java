package br.usp.poli.magnodb.model.dao;

import br.usp.poli.magnodb.model.Cliente;

import javax.sql.DataSource;
import javax.naming.NamingException;
import java.sql.*;

/**
 * Created by mateus on 06/12/16.
 */
public class ClienteDAO extends DBConnector {

    private static ClienteDAO instance;

    public static void setUpDAO(DataSource dataSource) {
        instance = new ClienteDAO(dataSource);
    }

    private ClienteDAO(DataSource dataSource) {
        try {
            create(dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static ClienteDAO getInstance() {
        return instance;
    }

    public void cadastrarCliente(Cliente cliente) {
        try {
            connect();
            Connection con = getConnection();

            PreparedStatement statement = con.prepareStatement("INSERT INTO Cliente " +
                    "(endereco, nome, email, telefone) " +
                    "VALUES (?, ?, ?, ?)");
            statement.setString(1, cliente.getEndereço());
            statement.setString(2, cliente.getNome());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getTelefone());

            statement.executeUpdate();

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cliente buscarCliente(int id) {
        Cliente cliente = null;

        try {
            connect();
            Connection con = getConnection();

            PreparedStatement statment = con.prepareStatement("SELECT * FROM Cliente WHERE  id = ?");
            statment.setInt(1, id);

            ResultSet rs = statment.executeQuery();
            if (rs.next()) {
                cliente = new Cliente(rs.getString("endereco"), rs.getString("nome"), rs.getString("email"), rs.getString("telefone"));
                cliente.setId(rs.getInt("id"));
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }
}
