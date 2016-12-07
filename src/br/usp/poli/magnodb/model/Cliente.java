package br.usp.poli.magnodb.model;

/**
 * Created by mateus on 06/12/16.
 */
public abstract class Cliente {

    private int id;
    private String endereço;
    private String nome;
    private String email;
    private String telefone;


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

    public int getId() {
        return id;
    }
}
