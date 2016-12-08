package br.usp.poli.magnodb.controller;

import br.usp.poli.magnodb.model.Context;
import br.usp.poli.magnodb.model.Entrega;
import br.usp.poli.magnodb.model.Produto;
import br.usp.poli.magnodb.model.dao.EntregaDAO;
import br.usp.poli.magnodb.model.dao.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.List;

/**
 * Created by mateus on 08/12/16.
 */
public class EntregaListaController {
    @FXML
    TableView tabela;

    @FXML
    public void initialize() {
        EntregaDAO dao = EntregaDAO.getInstance();

        ObservableList<Entrega> entregaList = FXCollections.observableArrayList();
        Context.getInstance().getContentPane();
        List<Entrega> entregas = Context.getInstance().getEntregas();
        entregas.forEach(entregaList::add);
        tabela.setItems(entregaList);
    }
}
