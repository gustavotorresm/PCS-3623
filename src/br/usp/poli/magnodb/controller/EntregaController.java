package br.usp.poli.magnodb.controller;

import br.usp.poli.magnodb.model.dao.EntregaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by mateus on 07/12/16.
 */
public class EntregaController {

    @FXML
    TextField tf_tempoMedio;

    @FXML
    TextField tf_numeroEntregasRecebidas;

    @FXML
    TextField tf_mediaFrete;

    @FXML
    public void initialize() {
        EntregaDAO dao = EntregaDAO.getInstance();
        float tempoMedio = dao.calcularTempoMedio();
        float mediaFrete = dao.calcularMediaFrete();
        int numeroentregasRecebidas = dao.calcularNumeroEntregasRecebidas();

        tf_mediaFrete.setText(Float.toString(mediaFrete));
        tf_tempoMedio.setText(Float.toString(tempoMedio));
        tf_numeroEntregasRecebidas.setText(Integer.toString(numeroentregasRecebidas));
    }
}
