package br.usp.poli.magnodb.model;

/**
 * Created by mateus on 06/12/16.
 */
public class Cliente {

    private int id;
    private String endereço;
    private String nome;
    private String email;
    private String telefone;

    public Cliente(String endereço, String nome, String email) {
        this.endereço = endereço;
        this.nome = nome;
        this.email = email;
    }

    public String getEndereço() {
        return endereço;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
}
