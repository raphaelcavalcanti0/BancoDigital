package com.rcfin.models;

import com.rcfin.controle.Controles;

import java.math.BigDecimal;

public class MovimentosConta {

    private String movimento;
    private BigDecimal valor;
    private String documento;

    public MovimentosConta(String movimento, BigDecimal valor) {
        this.movimento = movimento;
        this.valor = valor;
        this.documento = String.valueOf(Controles.IDENTIFICADOR_MOVIMENTO);
        Controles.IDENTIFICADOR_MOVIMENTO++;
    }

    public String getMovimento() {
        return movimento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDocumento() {
        return documento;
    }
}
