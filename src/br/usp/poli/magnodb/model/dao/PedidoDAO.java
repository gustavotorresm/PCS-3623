package br.usp.poli.magnodb.model.dao;

import br.usp.poli.magnodb.model.Pedido;
import br.usp.poli.magnodb.model.Produto;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
