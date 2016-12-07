package br.usp.poli.magnodb.model;

import java.util.Date;

public class Produto {

    private String nome, descricao;
    private Date criacao;

    private int id;

    public Produto(String nome, String descricao, Date criacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.criacao = criacao;
    }

    public Produto(String nome, String descricao) {
        this(nome, descricao, new Date());
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
