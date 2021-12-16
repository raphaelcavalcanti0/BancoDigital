package com.rcfin.models;

import java.math.BigDecimal;

public interface IConta {

    default void sacar(BigDecimal valor) {}
    default void depositar(BigDecimal valor) {}
    default void depositar(BigDecimal valor, MovimentosConta mov) {}
    default void consultarSaldo() {}
    default void transferir(BigDecimal valor, Conta contaDestino) {}
    default void consultarExtrato() {}
    default void depositarEmprestimo(BigDecimal valor) {}
    default void pagamento() {}
}
