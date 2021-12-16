package com.rcfin.models;

public class ContaPoupança extends Conta {

    public ContaPoupança(Agencia agencia, Cliente cliente) {
        super(agencia, cliente);
    }

    @Override
    public void consultarExtrato() {
        System.out.println("================ EXTRATO DE CONTA POUPANÇA (INICIO) ================");
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

        System.out.println("================ EXTRATO DE CONTA POUPANÇA (FIM) ===================");
        consultarSaldo();
    }
}
