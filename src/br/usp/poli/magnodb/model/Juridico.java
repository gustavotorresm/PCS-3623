package br.usp.poli.magnodb.model;

/**
 * Created by mateus on 06/12/16.
 */
public class Juridico extends Cliente {

    private String cnpj;
    private String porte;
    private String tipo;

    public Juridico(String endereço, String nome, String email, String telefone, String cnpj, String porte, String tipo) {

        super.setEndereço(endereço);
        super.setNome(nome);
        super.setEmail(email);
        super.setTelefone(telefone);
        this.cnpj = cnpj;
        this.porte = porte;
        this.tipo = tipo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getPorte() {
        return porte;
    }

    public String getTipo() {
        return tipo;
    }
}
