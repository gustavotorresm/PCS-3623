package br.usp.poli.magnodb.model.dao;

import br.usp.poli.magnodb.model.Usuario;
import br.usp.poli.magnodb.model.dao.config.DAOSetUp;
import org.junit.Test;

import static org.junit.Assert.*;

public class UsuarioDAOTest {
    @Test
    public void cadastrarUsuario() throws Exception {
        DAOSetUp.productionSetUp();

        Usuario usuario = new Usuario("magno", "maria");

        UsuarioDAO dao = UsuarioDAO.getInstance();

        dao.cadastrarUsuario(usuario);

    }

}