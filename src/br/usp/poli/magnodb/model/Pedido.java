package br.usp.poli.magnodb.model;

import java.util.Date;

/**
 * Created by mateus on 06/12/16.
 */
public class Pedido {
    private int id;
    private Date data;

    public Pedido(Date data) {
        this.data = data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

	public int getId() {
		return this.id;
	}

}
