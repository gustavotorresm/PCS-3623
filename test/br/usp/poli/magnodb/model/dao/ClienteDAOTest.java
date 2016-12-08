package br.usp.poli.magnodb.model.dao;

import br.usp.poli.magnodb.model.Cliente;
import br.usp.poli.magnodb.model.Fisico;
import br.usp.poli.magnodb.model.Juridico;
import br.usp.poli.magnodb.model.Produto;
import br.usp.poli.magnodb.model.dao.config.DAOSetUp;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by levy on 06/12/16.
 */
public class ClienteDAOTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void cadastrarClienteJuridico() throws Exception {
        Cliente cliente = new Juridico("rua das flores", "mateus", "mateus@mateus",
                "3333", "111", "1", "Juridico");
        DAOSetUp.testSetUp();

        ClienteDAO dao = ClienteDAO.getInstance();
        dao.cadastrarCliente(cliente);
    }

    @Test
    public void cadastrarClienteFisico() throws Exception {
        Cliente cliente = new Fisico("rua das flores", "mateus", "mateus@mateus",
                "3333", "111", "333", "500");
        DAOSetUp.testSetUp();

        ClienteDAO dao = ClienteDAO.getInstance();
        dao.cadastrarCliente(cliente);
    }

    @Test
    public void buscarCliente() throws Exception {
        int id = 1;
        DAOSetUp.testSetUp();
        Cliente cliente = ClienteDAO.getInstance().buscarCliente(id);
        System.out.println(cliente.getNome());
    }

    @Test
    public void listarClientes() throws Exception {
        DAOSetUp.testSetUp();
        List<Cliente> clientes = ClienteDAO.getInstance().listarClientes();
        for (Cliente cliente: clientes) {
            System.out.println(cliente.getNome());
        }
    }
}