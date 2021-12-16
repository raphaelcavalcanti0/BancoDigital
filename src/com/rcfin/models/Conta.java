package com.rcfin.models;


import com.rcfin.controle.Controles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Conta implements IConta {

    protected Agencia agencia;
    protected String numeroConta;
    protected Cliente cliente;
    protected BigDecimal saldo;
    protected String moeda;
    protected List<MovimentosConta> movimentosContaList = new ArrayList<>();

    public Conta(Agencia agencia, Cliente cliente) {
        this.agencia = agencia;
        this.numeroConta = String.valueOf(Controles.IDENTIFICADOR_CONTA);
        this.cliente = cliente;
        this.saldo = BigDecimal.valueOf(0d);
        this.moeda = "R$";
        Controles.IDENTIFICADOR_CONTA++;
    }

    @Override
    public void sacar(BigDecimal valor) {
        if (this.saldo.compareTo(valor) < 0) {
            this.saldo = saldo.subtract(valor);
            MovimentosConta mov = new MovimentosConta("Saque", valor);
            movimentosContaList.add(mov);
        } else {
            System.out.println("[!] Saldo insuficiente para saque. Consulte seu saldo!");
        }
    }

    @Override
    public void depositar(BigDecimal valor) {
        this.saldo = saldo.add(valor);
        MovimentosConta mov = new MovimentosConta("Depósito", valor);
        movimentosContaList.add(mov);
    }

    @Override
    public void depositar(BigDecimal valor, MovimentosConta mov) {
        this.saldo = saldo.add(valor);
        movimentosContaList.add(mov);
    }

    @Override
    public void depositarEmprestimo(BigDecimal valor) {
        this.saldo = saldo.add(valor);
        MovimentosConta mov = new MovimentosConta("Empréstimo", valor);
        movimentosContaList.add(mov);
    }

    @Override
    public void consultarSaldo() {
        System.out.println("[=] Saldo em conta: " + moeda + String.format("%,.2f", saldo));
    }

    @Override
    public void transferir(BigDecimal valor, Conta contaDestino) {
        if (this.saldo.compareTo(valor) >= 0) {
            this.sacar(valor);
            MovimentosConta mov = new MovimentosConta("Transferência", valor);
            contaDestino.depositar(valor, mov);
            movimentosContaList.add(mov);
        } else {
            System.out.println("[!] Saldo insuficiente para realizar a transferência. Consulte seu saldo!");
        }
    }

    @Override
    public void consultarExtrato() {
        System.out.println("================ EXTRATO DE CONTA (INICIO) ================");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Agência: " + agencia.getNumero() + "\t" + "N° Conta: " + numeroConta);
        System.out.println("===========================================================");
        System.out.println(colunaFixa("OPERACAO REALIZADA") + colunaFixa("N° DOCUMENTO") + colunaFixa("VALOR"));

        if (movimentosContaList.isEmpty()) {
            System.out.println("\nSem movimentações!\n");
        } else {
            for (MovimentosConta mov : movimentosContaList) {
                System.out.println(colunaFixa(mov.getMovimento()) + colunaFixa(mov.getDocumento()) + colunaFixa(moeda + String.format("%,.2f", mov.getValor())));
            }
        }

        System.out.println("================ EXTRATO DE CONTA (FIM) ===================");
        consultarSaldo();
    }

    protected String colunaFixa(String s) {
        int tamanhoColuna = 25;
        int tamanhoString = s.length();
        String retorno = "                             ";

        if (tamanhoColuna < tamanhoString) {
            retorno = s.substring(0, tamanhoColuna - 2);
        } else {
            retorno = s + retorno;
            retorno = retorno.substring(0, tamanhoColuna - 2);
        }

        return retorno;
    }

}
