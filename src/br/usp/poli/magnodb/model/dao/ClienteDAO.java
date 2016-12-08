package br.usp.poli.magnodb.model.dao;

import br.usp.poli.magnodb.model.Cliente;
import br.usp.poli.magnodb.model.Fisico;
import br.usp.poli.magnodb.model.Juridico;

import javax.sql.DataSource;
import javax.naming.NamingException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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

            if(cliente instanceof Fisico) {
                statement = con.prepareStatement("INSERT INTO Fisico " +
                        "(id, cpf, rg, renda) " +
                        "VALUES (LAST_INSERT_ID(), ?, ?, ?)");
                statement.setString(1, ((Fisico) cliente).getCpf());
                statement.setString(2, ((Fisico) cliente).getRg());
                statement.setString(3, ((Fisico) cliente).getRenda());

                statement.executeUpdate();
            } else if(cliente instanceof  Juridico) {
                statement = con.prepareStatement("INSERT INTO Juridico " +
                        "(id, cnpj, porte, tipo) " +
                        "VALUES (LAST_INSERT_ID(), ?, ?, ?)");
                statement.setString(1, ((Juridico) cliente).getCnpj());
                statement.setString(2, ((Juridico) cliente).getPorte());
                statement.setString(3, ((Juridico) cliente).getTipo());

                statement.executeUpdate();
            }

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

            PreparedStatement statement = con.prepareStatement("SELECT * FROM Cliente WHERE  id = ?");
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String endereco = rs.getString("endereco");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                statement = con.prepareStatement("SELECT * FROM Fisico WHERE id = ?");
                statement.setInt(1, id);

                rs = statement.executeQuery();
                if (rs.next()) {
                    cliente = new Fisico(endereco, nome, email, telefone, rs.getString("cpf"), rs.getString("rg"), rs.getString("renda"));
                    cliente.setId(rs.getInt("id"));
                } else {
                    statement = con.prepareStatement("SELECT * FROM Juridico WHERE id = ?");
                    statement.setInt(1, id);

                    rs = statement.executeQuery();
                    if (rs.next()) {
                        cliente = new Juridico(endereco, nome, email, telefone, rs.getString("cnpj"), rs.getString("porte"), rs.getString("tipo"));
                        cliente.setId(rs.getInt("id"));
                    }
                }
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new LinkedList<>();
        try{
            connect();
            Connection con = getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT * FROM Cliente " +
                    "LEFT JOIN Fisico ON Cliente.id = Fisico.id " +
                    "LEFT JOIN Juridico ON Cliente.id = Juridico.id");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Cliente cliente = null;
                String endereco = rs.getString("endereco");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                int id = rs.getInt("Cliente.id");
                int juridicoId = rs.getInt("Juridico.id");
                if(rs.wasNull()) {
                    cliente = new Fisico(endereco, nome, email, telefone, rs.getString("cpf"), rs.getString("rg"),
                            rs.getString("renda"));
                } else {
                    cliente = new Juridico(endereco, nome, email, telefone, rs.getString("cnpj"),
                            rs.getString("porte"), rs.getString("tipo"));
                }
                cliente.setId(id);
                clientes.add(cliente);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}
