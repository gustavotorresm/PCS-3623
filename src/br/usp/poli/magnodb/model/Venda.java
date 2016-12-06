package br.usp.poli.magnodb.model;

import java.util.Date;
import usp.poli.magnodb.model.Cliente;

/**
 * Created by mateus on 06/12/16.
 */
public class Venda {
    private Cliente cliente;
    private Entrega entrega;
    private Pedido pedido;
    private Date dataPagamento;
    private float desconto;
    private String notaFiscal;
}
