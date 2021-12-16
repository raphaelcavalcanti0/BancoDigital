package com.rcfin.models;

import com.rcfin.controle.Controles;

public class MovimentosConta {

    private String movimento;
    private double valor;
    private String documento;

    public MovimentosConta(String movimento, double valor) {
        this.movimento = movimento;
        this.valor = valor;
        this.documento = String.valueOf(Controles.IDENTIFICADOR_MOVIMENTO);
        Controles.IDENTIFICADOR_MOVIMENTO++;
    }

    public String getMovimento() {
        return movimento;
    }

    public double getValor() {
        return valor;
    }

    public String getDocumento() {
        return documento;
    }
}
