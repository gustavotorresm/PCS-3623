package br.usp.poli.magnodb.model.dao;

import br.usp.poli.magnodb.model.Cliente;
import br.usp.poli.magnodb.model.Entrega;
import br.usp.poli.magnodb.model.Pedido;
import br.usp.poli.magnodb.model.Venda;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
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

    public void cadastrarVenda(Venda venda) {
        try {
            connect();
            Connection con = getConnection();

            PreparedStatement statement = con.prepareStatement("INSERT INTO Venda " +
                    "VALUES (?, ?, ?, ?, ?, ?)");
            statement.setInt(1, venda.getCliente().getId());
            statement.setInt(2, venda.getEntrega().getId());
            statement.setInt(3, venda.getPedido().getId());
            statement.setTimestamp(4, new Timestamp(venda.getDataPagamento().getTime()));
            statement.setFloat(5, venda.getDesconto());
            statement.setString(6, venda.getNotaFiscal());

            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
