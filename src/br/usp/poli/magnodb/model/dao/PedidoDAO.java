package br.usp.poli.magnodb.model.dao;

import br.usp.poli.magnodb.model.Pedido;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by mateus on 06/12/16.
 */
public class PedidoDAO extends DBConnector {

    private static PedidoDAO instance;

    public static void setUpDAO(DataSource dataSource) {
        instance = new PedidoDAO(dataSource);
    }

    private PedidoDAO(DataSource dataSource) {
        try {
            create(dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static PedidoDAO getInstance() {
        return instance;
    }

    public Set<Pedido> listarPedidos() {
        Set<Pedido> pedidos = new TreeSet<>();
        try {
            connect();
            Connection con = getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT * FROM Pedido");

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido(rs.getDate("data"));
                pedido.setId(rs.getInt("id"));
                pedidos.add(pedido);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }

    public Pedido buscarPedido(int id) {
    	System.out.println("Caramba!");
        Pedido pedido = null;
        try {
            connect();
            Connection con = getConnection();

            PreparedStatement statment = con.prepareStatement("SELECT * FROM Pedido WHERE id = ?");
            statment.setInt(1, id);

            ResultSet rs = statment.executeQuery();
            if (rs.next()) {
                pedido = new Pedido(rs.getTimestamp("data"));
                pedido.setId(rs.getInt("id"));
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedido;
    }

    public String buscarCliente(int id) {
        String cliente = null;
        try {
            connect();
            Connection con = getConnection();

            PreparedStatement statment = con.prepareStatement("SELECT nome FROM Pedido P, Cliente C, Venda V WHERE (P.id = V.pedido and V.cliente = C.id and P.id = ?)");
            statment.setInt(1, id);

            ResultSet rs = statment.executeQuery();
            if (rs.next()) {
                cliente = rs.getString("nome");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }
    
    public int buscarEntrega(int id) {
    	int entrega = 0;
        try {
            connect();
            Connection con = getConnection();

            PreparedStatement statment = con.prepareStatement("SELECT entrega FROM Pedido P, Cliente C, Venda V WHERE (P.id = V.pedido and V.cliente = C.id and P.id = ?)");
            statment.setInt(1, id);

            ResultSet rs = statment.executeQuery();
            if (rs.next()) {
                entrega = rs.getInt("entrega");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrega;
    }

    public int cadastrarPedido(Pedido pedido) {
        int id = -1;
        try {
            connect();
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("INSERT INTO Pedido " +
                    "(data) " +
                    "VALUES (?)");
            statement.setDate(1, new Date(pedido.getData().getTime()));

            statement.executeUpdate();

            statement = con.prepareStatement("SELECT LAST_INSERT_ID() AS id");
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                id = rs.getInt("id");
            }

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;

    }
}
