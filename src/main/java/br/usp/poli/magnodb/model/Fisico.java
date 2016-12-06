package br.usp.poli.magnodb.model;

/**
 * Created by mateus on 06/12/16.
 */
public class Fisico extends Cliente {

    private String cpf;
    private String rg;
    private String renda;

    public Fisico(String endereço, String nome, String email, String telefone, String cpf, String rg, String renda) {
        super(endereço, nome, email, telefone);
        this.cpf = cpf;
        this.rg = rg;
        this.renda = renda;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public String getRenda() {
        return renda;
    }
}
