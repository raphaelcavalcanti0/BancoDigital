package com.rcfin.models;

public interface IConta {

    default void sacar(double valor) {}
    default void depositar(double valor) {}
    default void consultarSaldo() {}
    default void transferir(double valor, Conta contaDestino) {}
    default void consultarExtrato() {}
    default void pagamento() {}
}
