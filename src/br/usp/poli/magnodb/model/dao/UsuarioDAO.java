package br.usp.poli.magnodb.model.dao;

import br.usp.poli.magnodb.model.Cliente;
import br.usp.poli.magnodb.model.Produto;
import br.usp.poli.magnodb.model.Usuario;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Set;
import java.util.TreeSet;

public class UsuarioDAO extends DBConnector {
    private static UsuarioDAO instance;

    public static void setUpDAO(DataSource dataSource) {
        instance = new UsuarioDAO(dataSource);
    }

    private UsuarioDAO(DataSource dataSource) {
        try {
            create(dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public Usuario buscarUsuario(String username) {
        Usuario usuario = null;

        try {
            connect();
            Connection con = getConnection();

            PreparedStatement statment = con.prepareStatement("SELECT * FROM Usuario WHERE  username = ?");
            statment.setString(1, username);

            ResultSet rs = statment.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(rs.getString("username"), rs.getBoolean("root"), rs.getBytes("salt"), rs.getBytes("password"));
                usuario.setId(rs.getInt("id"));
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public void cadastrarUsuario(Usuario usuario) throws Exception {

        try {
            connect();
            Connection con = getConnection();

            PreparedStatement statment = con.prepareStatement("INSERT INTO " +
                    "Usuario (username, password, salt, root) " +
                    "VALUES (?, ?, ?, ?)");
            statment.setString(1, usuario.getUsername());
            statment.setBytes(2, usuario.getPasswordHash());
            statment.setBytes(3, usuario.getSalt());
            statment.setBoolean(4, usuario.isRoot());

            System.out.println(usuario.getSalt().length);

            int modified = statment.executeUpdate();

            con.close();

            if (modified != 1) {
                throw new Exception();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static UsuarioDAO getInstance() {
        return instance;
    }
}
