package br.usp.poli.magnodb.model.dao.config;

import br.usp.poli.magnodb.model.dao.*;
import br.usp.poli.magnodb.model.dao.context.LocalContext;
import br.usp.poli.magnodb.model.dao.context.LocalContextFactory;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class DAOSetUp {

    private static LocalContext lc;

    public static void productionSetUp() {
        try {
            load("production");
            setUp("production");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void load(String env) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("db.properties"));

        String driver = prop.getProperty(env + ".driver");
        String url = prop.getProperty(env + ".url");
        String database = prop.getProperty(env + ".schema");
        String username = prop.getProperty(env + ".user");
        String password = prop.getProperty(env + ".password");

        lc = LocalContextFactory.createLocalContext(driver);
        lc.addDataSource(env, url + "/" + database, username, password);
    }

    private static void setUp(String env) throws NamingException {
        ProdutoDAO.setUpDAO((DataSource) lc.lookup(env));
        ClienteDAO.setUpDAO((DataSource) lc.lookup(env));
        UsuarioDAO.setUpDAO((DataSource) lc.lookup(env));
        EntregaDAO.setUpDAO((DataSource) lc.lookup(env));
        ItemDAO.setUpDAO((DataSource) lc.lookup(env));
        PedidoDAO.setUpDAO((DataSource) lc.lookup(env));
        VendaDAO.setUpDAO((DataSource) lc.lookup(env));
    }

    public static void testSetUp() {
        try {
            load("test");
            setUp("test");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
