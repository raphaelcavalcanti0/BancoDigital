package com.rcfin.models;

import com.rcfin.controle.Controles;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Emprestimo {

    public enum tipoAmortizacao {SAC, PRICE}

    private final String contrato;
    private Agencia agencia;
    private Cliente cliente;
    private Conta conta;
    private Calendar dataContratacao;
    private tipoAmortizacao tipoAmortiza;
    private BigDecimal valorSolicitado;
    private BigDecimal valorFinanciado;
    private BigDecimal taxaJuros;
    private BigDecimal taxaMora;
    private BigDecimal taxaAtraso;
    private int numeroParcelas;
    private int diaVencimento;
    private int ultimoVencimento;
    private List<Parcela> parcelaList = new ArrayList<>();


    public Emprestimo(Agencia agencia, Cliente cliente, Conta conta,
                      Calendar dataContratacao, tipoAmortizacao tipoAmortiza,
                      BigDecimal valorSolicitado, BigDecimal taxaJuros,
                      BigDecimal taxaMora, BigDecimal taxaAtraso,
                      int numeroParcelas, int diaVencimento) throws ParseException {
        this.contrato = String.valueOf(Controles.IDENTIFICADOR_CONTRATO);
        this.agencia = agencia;
        this.cliente = cliente;
        this.conta = conta;
        this.dataContratacao = dataContratacao;
        this.tipoAmortiza = tipoAmortiza;
        this.valorSolicitado = valorSolicitado;
        this.valorFinanciado = valorSolicitado.multiply(Controles.TAXA_IOF_UNICO)
                .add(valorSolicitado.multiply(BigDecimal.valueOf(numeroParcelas).multiply(Controles.TAXA_IOF_DIA))
                        .multiply(BigDecimal.valueOf(30))).add(valorSolicitado).setScale(2, RoundingMode.HALF_EVEN);
        this.taxaJuros = taxaJuros;
        this.taxaMora = taxaMora;
        this.taxaAtraso = taxaAtraso;
        this.numeroParcelas = numeroParcelas;
        this.diaVencimento = diaVencimento;
        Controles.IDENTIFICADOR_CONTRATO++;

        if (tipoAmortiza == tipoAmortizacao.PRICE) {
            BigDecimal juros_um = taxaJuros.add(BigDecimal.valueOf(1));
            BigDecimal valorParcelaVencimento = juros_um.pow(numeroParcelas).multiply(taxaJuros)
                    .divide((juros_um.pow(numeroParcelas).subtract(BigDecimal.valueOf(1))),4, RoundingMode.HALF_EVEN);
            valorParcelaVencimento = valorParcelaVencimento.multiply(valorFinanciado).setScale(2, RoundingMode.HALF_EVEN);

            List<Date> vencimentos = vencimentos(diaVencimento, numeroParcelas);
            for (int numParc = 1; numParc <= numeroParcelas; numParc++) {
                Date dataVencimento = vencimentos.get(numParc - 1);

                BigDecimal montanteJuros = BigDecimal.valueOf(0d);
                BigDecimal montanteAmortizacao = BigDecimal.valueOf(0d);

                Parcela parcela = new Parcela(contrato, numParc,
                        dataVencimento,
                        montanteJuros, montanteAmortizacao,
                        valorParcelaVencimento,
                        taxaAtraso, taxaMora, taxaJuros);
                parcelaList.add(parcela);
            }
        }

        conta.depositarEmprestimo(valorSolicitado);

    }

    private List<Date> vencimentos(int diaVencimento, int numeroParcelas) throws ParseException {
        Calendar hojeCalendar = Calendar.getInstance();

        int mes = hojeCalendar.get(Calendar.MONTH) + 1;
        int ano = hojeCalendar.get(Calendar.YEAR);

        int novoDia = diaVencimento;
        int novoMes = mes;
        int novoAno = ano;

        List<Date> vencimentos = new ArrayList<>();

        for (int val = 1; val <= numeroParcelas; val++) {
            if ((novoMes + 1) > 12) {
                novoMes = 1;
                novoAno++;
            } else {
                novoMes++;
            }
            Date newDate = new SimpleDateFormat("dd/MM/yyyy")
                    .parse(novoDia + "/" + novoMes + "/" + novoAno);
            Calendar c = Calendar.getInstance();
            c.setTime(newDate);
            if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                novoDia += 2;
                newDate = new SimpleDateFormat("dd/MM/yyyy")
                        .parse(novoDia + "/" + novoMes + "/" + novoAno);
            } else if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                novoDia += 1;
                newDate = new SimpleDateFormat("dd/MM/yyyy")
                        .parse(novoDia + "/" + novoMes + "/" + novoAno);
            }
            vencimentos.add(newDate);
            novoDia = diaVencimento;
        }

        return vencimentos;
    }

    public void consultarParcelas() {
        for (Parcela parcela : parcelaList) {
            System.out.println("Contrato: " + parcela.getContrato() +
                    " Num. Parcela: " + parcela.getNumeroParcela() +
                    " Vencimento: " + parcela.getDataVencimento() +
                    " Valor: " + parcela.getValorParcelaVencimento());
        }
    }

    public void consultarEmprestimo() {
        System.out.println("Contrato: " + contrato +
                " Agência: " + agencia.getNumero() +
                " Cliente: " + cliente.getNome() +
                " Valor: " + valorFinanciado);
    }

}
