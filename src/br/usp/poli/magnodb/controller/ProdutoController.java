package br.usp.poli.magnodb.controller;

import br.usp.poli.magnodb.model.Context;
import br.usp.poli.magnodb.model.Produto;
import br.usp.poli.magnodb.model.dao.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

public class ProdutoController {

    @FXML
    Label nomeLabel;

    @FXML
    TextArea descricaoField;

    @FXML
    ChoiceBox<Date> precoChoice;

    @FXML
    Label precoLabel;

    private Produto produto;
    private HashMap<Date, Float> precos;

    public ProdutoController() {
        produto = Context.getInstance().getProduto();

        ProdutoDAO dao = ProdutoDAO.getInstance();
        precos = dao.getPrecos(produto);
    }

    @FXML
    public void initialize() {
        nomeLabel.setText(produto.getNome());
        descricaoField.setText(produto.getDescricao());

        ObservableList<Date> datas = FXCollections.observableArrayList();
        datas.addAll(precos.keySet());

        precoChoice.setItems(datas);
        precoChoice.setValue(precos.keySet().parallelStream().max(Comparator.comparingLong(x -> x.getTime())).get());

        precoLabel.setText(String.valueOf(produto.getPreco()));
    }

    @FXML
    public void atualizarPreco(ActionEvent e) {
        TextInputDialog dialog = new TextInputDialog(String.valueOf(produto.getPreco()));
        dialog.setTitle("Atualizar preço");
        dialog.setHeaderText("Digite o novo valor do produto " + produto.getNome());
        dialog.setContentText("Preço: ");

        Optional<Float> preco = dialog.showAndWait().map(Float::valueOf);

        ProdutoDAO dao = ProdutoDAO.getInstance();

        dao.atualizarPreco(preco.get(), produto);

        Date data = new Date();
        precos.put(data, preco.get());
        precoChoice.getItems().add(data);
    }

    public void exibePreco(ActionEvent actionEvent) {
        precoLabel.setText(String.valueOf(precos.get(precoChoice.getValue())));
    }
}
