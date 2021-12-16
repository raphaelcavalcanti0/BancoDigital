package com.rcfin.models;


import com.rcfin.controle.Controles;

import java.util.ArrayList;
import java.util.List;

public class Conta implements IConta {

    protected Agencia agencia;
    protected String numeroConta;
    protected Cliente cliente;
    protected double saldo;
    protected String moeda;
    protected List<MovimentosConta> movimentosContaList = new ArrayList<>();

    public Conta(Agencia agencia, Cliente cliente) {
        this.agencia = agencia;
        this.numeroConta = String.valueOf(Controles.IDENTIFICADOR_CONTA);
        this.cliente = cliente;
        this.saldo = 0d;
        this.moeda = "R$";
        Controles.IDENTIFICADOR_CONTA++;
    }

    @Override
    public void sacar(double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            MovimentosConta mov = new MovimentosConta("Saque", valor);
            movimentosContaList.add(mov);
        } else {
            System.out.println("[!] Saldo insuficiente para saque. Consulte seu saldo!");
        }
    }

    @Override
    public void depositar(double valor) {
        this.saldo += valor;
        MovimentosConta mov = new MovimentosConta("Depósito", valor);
        movimentosContaList.add(mov);
    }

    @Override
    public void consultarSaldo() {
        System.out.println("[=] Saldo em conta: " + moeda + String.format("%,.2f", saldo));
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        if (this.saldo >= valor) {
            this.sacar(valor);
            contaDestino.depositar(valor);
            MovimentosConta mov = new MovimentosConta("Transferência", valor);
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
