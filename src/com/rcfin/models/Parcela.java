package com.rcfin.models;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parcela {

    public enum empSituacao {VENCIDA, NORMAL}

    private int numeroParcela;
    private Date dataVencimento;
    private String contrato;
    private BigDecimal montanteJuros;
    private BigDecimal montanteAmortizacao;
    private BigDecimal valorParcelaVencimento;
    private BigDecimal valorJurosAtraso;
    private BigDecimal valorMoraAtraso;
    private BigDecimal valorParcelaAtraso;
    private BigDecimal jurosContrato;
    private BigDecimal jurosMora;
    private BigDecimal jurosAtraso;
    private empSituacao situacao;

    public Parcela(String contrato, int numeroParcela, Date dataVencimento, BigDecimal montanteJuros,
                   BigDecimal montanteAmortizacao, BigDecimal valorParcelaVencimento,
                   BigDecimal jurosContrato, BigDecimal jurosMora, BigDecimal jurosAtraso) {
        this.numeroParcela = numeroParcela;
        this.contrato = contrato;
        this.dataVencimento = dataVencimento;
        this.montanteJuros = montanteJuros;
        this.montanteAmortizacao = montanteAmortizacao;
        this.valorParcelaVencimento = valorParcelaVencimento;
        this.jurosContrato = jurosContrato;
        this.valorParcelaAtraso = BigDecimal.valueOf(0d);
        this.valorJurosAtraso = BigDecimal.valueOf(0d);
        this.valorMoraAtraso = BigDecimal.valueOf(0d);
        this.jurosMora = jurosMora;
        this.jurosAtraso = jurosAtraso;
        this.situacao = empSituacao.NORMAL;
    }


    public int getNumeroParcela() {
        return numeroParcela;
    }

    public String getDataVencimento() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(dataVencimento);
    }

    public String getContrato() {
        return contrato;
    }

    public BigDecimal getValorParcelaVencimento() {
        return valorParcelaVencimento;
    }

    public empSituacao getSituacao() {
        return situacao;
    }
}
