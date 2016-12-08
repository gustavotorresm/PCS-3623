package br.usp.poli.magnodb.model;

import java.util.Date;

public class Produto implements Comparable {

    private String nome, descricao;
    private Date criacao;

    private Date ultimaVenda;
    private int quantidadeVendida;
    private float preco;

    private int id;

    public Produto(String nome, String descricao, Date criacao, float preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.criacao = criacao;
        this.preco = preco;
    }

    public Produto(String nome, String descricao, float preco) {
        this(nome, descricao, new Date(), preco);
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

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public Date getUltimaVenda() {
        return ultimaVenda;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }


    public void setUltimaVenda(Date ultimaVenda) {
        this.ultimaVenda = ultimaVenda;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    @Override
    public int compareTo(Object o) {
        return hashCode() - o.hashCode();
    }

    @Override
    public int hashCode() {
        return id;
    }
}
