package br.usp.poli.magnodb.model.dao;

import br.usp.poli.magnodb.model.Cliente;
import br.usp.poli.magnodb.model.Produto;
import br.usp.poli.magnodb.model.dao.config.DAOSetUp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mateus on 06/12/16.
 */
public class ClienteDAOTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void cadastrarCliente() throws Exception {
        Cliente c = new Cliente("Rua das flores", "Cláudio", "claudio@ime.usp.br", "1111-1111");
        DAOSetUp.testSetUp();;

        ClienteDAO dao = ClienteDAO.getInstance();
        dao.cadastrarCliente(c);
    }
}
