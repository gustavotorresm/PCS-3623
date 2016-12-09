package br.usp.poli.magnodb.controller;

import br.usp.poli.magnodb.model.Context;
import br.usp.poli.magnodb.model.Produto;
import br.usp.poli.magnodb.model.dao.ProdutoDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.DragEvent;
import javafx.util.StringConverter;
import org.controlsfx.control.RangeSlider;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class ProdutoListaController {

    @FXML
    TableView tabela;

    @FXML
    RangeSlider precoSlider, ultimaVendaSlider, quantidadeSlider;

    @FXML
    Accordion accordion;

    private ObservableList<Produto> produtosList;

    public float maxPreco, minPreco;
    public float highPreco, lowPreco;

    public Date maxUltimoVendido, minUltimoVendido;
    public Date highUltimoVendido, lowUltimoVendido;

    public int maxQuantidade, minQuantidade;
    public int highQuantidade, lowQuantidade;

    public ProdutoListaController() {
        ProdutoDAO dao = ProdutoDAO.getInstance();

        produtosList = FXCollections.observableArrayList();

        List<Produto> produtos = dao.listarProdutos();
        produtos.forEach(
                p ->{
                    System.out.println(p.getPreco());
                    System.out.println(p.getQuantidadeVendida());
                }
        );
        produtos.forEach(produtosList::add);

        maxPreco = produtos.parallelStream().max(Comparator.comparingDouble(Produto::getPreco)).map(Produto::getPreco).get();
        minPreco = produtos.parallelStream().min(Comparator.comparingDouble(Produto::getPreco)).map(Produto::getPreco).get();

        maxUltimoVendido = produtos.parallelStream().filter(p -> p.getUltimaVenda() != null).max(Comparator.comparing(p -> p.getUltimaVenda())).map(Produto::getUltimaVenda).get();
        minUltimoVendido = produtos.parallelStream().filter(p -> p.getUltimaVenda() != null).min(Comparator.comparing(p -> p.getUltimaVenda())).map(Produto::getUltimaVenda).get();

        maxQuantidade = produtos.parallelStream().max(Comparator.comparingInt(Produto::getQuantidadeVendida)).map(Produto::getQuantidadeVendida).get();
        minQuantidade = produtos.parallelStream().min(Comparator.comparingInt(Produto::getQuantidadeVendida)).map(Produto::getQuantidadeVendida).get();

        highPreco = maxPreco;
        lowPreco = minPreco;

        highQuantidade = maxQuantidade;
        lowQuantidade = minQuantidade;

        highUltimoVendido = maxUltimoVendido;
        lowUltimoVendido = minUltimoVendido;
    }

    @FXML
    public void initialize() {
        /*
         * Preço Slider
         */
        precoSlider.setMax(maxPreco);
        precoSlider.setMin(minPreco);

        precoSlider.setShowTickLabels(true);

        if (((maxPreco - minPreco) / 5) <= 0) {
        	precoSlider.setMajorTickUnit(0.1);
        } else {
        	precoSlider.setMajorTickUnit((maxPreco - minPreco) / 5);
        }

        precoSlider.setHighValue(maxPreco);
        precoSlider.setLowValue(minPreco);

        /*
         * Quantidade slider
         */
        quantidadeSlider.setMax(maxQuantidade);
        quantidadeSlider.setMin(minQuantidade);

        quantidadeSlider.setShowTickLabels(true);
        
        if ((maxQuantidade - minQuantidade) / 5d <= 0) {
        	quantidadeSlider.setMajorTickUnit(0.1);
        } else {
        	quantidadeSlider.setMajorTickUnit((maxQuantidade - minQuantidade) / 5d);
        }
        	
        quantidadeSlider.setHighValue(maxQuantidade);
        quantidadeSlider.setLowValue(minQuantidade);
        

        /*
         * Última venda slider
         */
        ultimaVendaSlider.setMax(maxUltimoVendido.getTime());
        ultimaVendaSlider.setMin(minUltimoVendido.getTime());

        ultimaVendaSlider.setShowTickLabels(true);

        if (((maxUltimoVendido.getTime() - minUltimoVendido.getTime()) / 3d) <= 0) {
        	ultimaVendaSlider.setMajorTickUnit(0.1);
        } else {
        	ultimaVendaSlider.setMajorTickUnit((maxUltimoVendido.getTime() - minUltimoVendido.getTime()) / 3d);
        }

        ultimaVendaSlider.setHighValue(maxUltimoVendido.getTime());
        ultimaVendaSlider.setLowValue(minUltimoVendido.getTime());

        ultimaVendaSlider.setLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number time) {
                LocalDate date = Instant.ofEpochMilli(time.longValue()).atZone(ZoneId.systemDefault()).toLocalDate();
                return date.getMonthValue() + "/" + (date.getYear() % 100);
            }

            @Override
            public Number fromString(String string) {
                long time = 0;
                try {
                    Date data = new SimpleDateFormat("MM/yy").parse(string);
                    time = data.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return time;
            }
        });
        
        ultimaVendaSlider.setVisible(false);

        tabela.setItems(produtosList);

        accordion.expandedPaneProperty().addListener((observable, oldValue, newValue) -> {
            Context.getInstance().getStage().sizeToScene();
        });
    }

    @FXML
    public void checkPrecoSlider() {
        boolean changed = false;

        if (lowPreco != precoSlider.getLowValue()) {
            lowPreco = (float) precoSlider.getLowValue();

            changed = true;
        }

        if (highPreco != precoSlider.getHighValue()) {
            highPreco = (float) precoSlider.getHighValue();

            changed = true;
        }

        if (changed) {
            filter();
        }
    }

    @FXML
    public void checkUltimaVendaSlider() {
        boolean changed = false;

        if (! lowUltimoVendido.equals(new Date((long) ultimaVendaSlider.getLowValue()))) {
            lowUltimoVendido = new Date((long) ultimaVendaSlider.getLowValue());

            changed = true;
        }

        if (! highUltimoVendido.equals(new Date((long) ultimaVendaSlider.getHighValue()))) {
            highUltimoVendido = new Date((long) ultimaVendaSlider.getHighValue());

            changed = true;
        }

        if (changed) {
            filter();
        }
    }

    @FXML
    public void checkQuantidadeSlider() {
        boolean changed = false;

        if (lowQuantidade != (int) quantidadeSlider.getLowValue()) {
            lowQuantidade = (int) quantidadeSlider.getLowValue();

            changed = true;
        }

        if (highQuantidade != (int) quantidadeSlider.getHighValue()) {
            highQuantidade = (int) quantidadeSlider.getHighValue();

            changed = true;
        }

        if (changed) {
            filter();
        }
    }

    private void filter() {
        ObservableList<Produto> newSearch = produtosList.filtered(produto -> produto.getPreco() >= lowPreco && produto.getPreco() <= highPreco);
        newSearch = newSearch.filtered(produto -> produto.getQuantidadeVendida() >= lowQuantidade && produto.getQuantidadeVendida() <= highQuantidade);
        // newSearch = newSearch.filtered(p -> p.getUltimaVenda() != null).filtered(produto -> produto.getUltimaVenda().compareTo(lowUltimoVendido) >= 0 && produto.getUltimaVenda().compareTo(highUltimoVendido) <= 0);

        tabela.setItems(newSearch);
    }
}
