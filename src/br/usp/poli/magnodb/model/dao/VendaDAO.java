package br.usp.poli.magnodb.model.dao;

import br.usp.poli.magnodb.model.Cliente;
import br.usp.poli.magnodb.model.Entrega;
import br.usp.poli.magnodb.model.Pedido;
import br.usp.poli.magnodb.model.Venda;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by mateus on 06/12/16.
 */
public class VendaDAO extends DBConnector {
    private static VendaDAO instance;

    public static void setUpDAO(DataSource dataSource) {
        instance = new VendaDAO(dataSource);
    }

    private VendaDAO(DataSource dataSource) {
        try {
            create(dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static VendaDAO getInstance() {
        return instance;
    }

    public List<Venda> buscarVendasPorCliente(Cliente cliente) {
        List<Venda> vendas = new LinkedList<Venda>();
        try {
            connect();
            Connection con = getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT * FROM Venda WHERE cliente = ?");
            statement.setInt(1, cliente.getId());

            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Entrega entrega = EntregaDAO.getInstance().buscarEntrega(rs.getInt("entrega"));
                Pedido pedido = PedidoDAO.getInstance().buscarPedido(rs.getInt("pedido"));
                Venda venda = new Venda(cliente, entrega, pedido, rs.getTimestamp("data_pagamento"),
                        rs.getFloat("desconto"), rs.getString("nota_fiscal"));
                vendas.add(venda);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vendas;
    }
}
