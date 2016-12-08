package br.usp.poli.magnodb.model.dao;

import br.usp.poli.magnodb.model.Produto;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DBConnector {
    private DataSource dataSource;
    private Connection connection;


    public void create(DataSource dataSource) throws NamingException {
        this.dataSource = dataSource;
    }

    protected void connect() throws SQLException {
        connection = dataSource.getConnection();
    }

    protected DataSource getDataSource() {
        return dataSource;
    }

    protected Connection getConnection() {
        return connection;
    }



}
