package br.usp.poli.magnodb.model;

import java.util.Date;

/**
 * Created by mateus on 06/12/16.
 */
public class Entrega {
    private int id;
    private String endereco;
    private float frete;
    private Date dataDespache;
    private Date dataRecepcao;

    public Entrega(String endereco, float frete, Date dataDespache, Date dataRecepcao) {
        this.endereco = endereco;
        this.frete = frete;
        this.dataDespache = dataDespache;
        this.dataRecepcao = dataRecepcao;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public float getFrete() {
        return frete;
    }

    public Date getDataDespache() {
        return dataDespache;
    }

    public Date getDataRecepcao() {
        return dataRecepcao;
    }
}
