package br.usp.poli.magnodb.model.dao;

import br.usp.poli.magnodb.model.dao.context.LocalContext;
import br.usp.poli.magnodb.model.dao.context.LocalContextFactory;

import java.util.Properties;

public class DBConnectorTest {
    @org.junit.Test
    public void create() throws Exception {
        DBConnector connector = new DBConnectorDummy();

    }

    @org.junit.Test
    public void connect() {

    }

}