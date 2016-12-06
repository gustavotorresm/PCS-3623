package br.usp.poli.magnodb.model;

import java.util.Date;
import usp.poli.magnodb.model.Produto;

/**
 * Created by mateus on 06/12/16.
 */
public class Preco {
    private Produto produto;
    private float preco;
    private Date data;
    private int id;

    public Preco(Produto produto, float preco, Date data) {
        this.produto = produto;
        this.preco = preco;
        this.data = data;
    }

    public Produto getProduto() {
        return produto;
    }

    public float getPreco() {
        return preco;
    }

    public Date getData() {
        return data;
    }

    public void setId(int id) {
        this.id = id;
    }
}
