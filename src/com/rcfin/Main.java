package com.rcfin;

import com.rcfin.controle.Controles;
import com.rcfin.models.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;


public class Main {

    public static void main(String[] args) throws ParseException {

        Agencia agencia1 = new Agencia("Primeira Agencia",
                new Endereco(Endereco.tipoEndereco.OUTRO,
                        "Rua da Alegria", "32", "",
                        "00000-000", "PE", "Recife"));

        Cliente cliente1 = new Cliente("Fulano", "000.000.000-01",
                new Endereco(Endereco.tipoEndereco.RESIDENCIAL,
                        "Rua de Fulano", "01", "",
                        "00100-000", "PE", "Recife"));

        Cliente cliente2 = new Cliente("Beltrano", "000.000.000-02",
                new Endereco(Endereco.tipoEndereco.RESIDENCIAL,
                        "Rua de Beltrano", "02", "",
                        "00300-000", "PE", "Recife"));

        Conta conta1 = agencia1.abrirConta(cliente1);
        Conta conta2 = agencia1.abrirConta(cliente2);

        conta1.consultarSaldo();
        conta1.depositar(BigDecimal.valueOf(100d));
        conta1.sacar(BigDecimal.valueOf(10d));
        conta1.consultarSaldo();
        conta1.consultarExtrato();
        conta2.consultarExtrato();
        conta1.transferir(BigDecimal.valueOf(50d), conta2);
        conta1.consultarExtrato();
        conta2.consultarExtrato();

        Emprestimo emprestimo1 = agencia1.novoEmprestimo(agencia1, cliente1, conta1, Calendar.getInstance(),
                Emprestimo.tipoAmortizacao.PRICE, BigDecimal.valueOf(1000.0d),
                Controles.TAXA_EMPRESTIMO_MENSAL,
                Controles.TAXA_MORA,
                Controles.TAXA_ATRASO_MENSAL,
                6, 15);

        Emprestimo emprestimo2 = agencia1.novoEmprestimo(agencia1, cliente2, conta2, Calendar.getInstance(),
                Emprestimo.tipoAmortizacao.PRICE, BigDecimal.valueOf(5000.0d),
                Controles.TAXA_EMPRESTIMO_MENSAL,
                Controles.TAXA_MORA,
                Controles.TAXA_ATRASO_MENSAL,
                24, 10);

        emprestimo1.consultarParcelas();
        emprestimo2.consultarParcelas();
        agencia1.listarEmprestimos();

        conta1.consultarExtrato();
        conta2.consultarExtrato();

    }
}
