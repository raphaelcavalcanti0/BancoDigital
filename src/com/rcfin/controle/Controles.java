package com.rcfin.controle;

import java.math.BigDecimal;

public class Controles {

    public static int IDENTIFICADOR_AGENCIA = 1;
    public static int IDENTIFICADOR_CONTA = 1;
    public static int IDENTIFICADOR_MOVIMENTO = 1000;
    public static int IDENTIFICADOR_CONTRATO = 100000;

    public static BigDecimal TAXA_EMPRESTIMO_MENSAL = new BigDecimal("0.05");
    public static BigDecimal TAXA_MORA = new BigDecimal("0.02");
    public static BigDecimal TAXA_ATRASO_MENSAL = new BigDecimal("0.01");
    public static BigDecimal TAXA_IOF_UNICO = new BigDecimal("0.0038");
    public static BigDecimal TAXA_IOF_DIA = new BigDecimal("0.000082");

}
