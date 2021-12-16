package com.rcfin.models;

import com.rcfin.controle.Controles;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Agencia {

    private String numero;
    private String nome;
    private Endereco endereco;
    private List<Emprestimo> emprestimoList = new ArrayList<>();

    public Agencia(String nome, Endereco endereco) {
        this.numero = String.valueOf(Controles.IDENTIFICADOR_AGENCIA);
        this.nome = nome;
        this.endereco = endereco;
        Controles.IDENTIFICADOR_AGENCIA++;
    }

    public String getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Conta abrirConta(Cliente cliente) {
        return new Conta(this, cliente);
    }

    public Emprestimo novoEmprestimo(Agencia agencia, Cliente cliente, Conta conta,
                                     Calendar dataContratacao, Emprestimo.tipoAmortizacao tipoAmortiza,
                                     BigDecimal valorSolicitado, BigDecimal taxaJuros,
                                     BigDecimal taxaMora, BigDecimal taxaAtraso,
                                     int numeroParcelas, int diaVencimento) throws ParseException {
        Emprestimo emprestimo = new Emprestimo(this,
                cliente,
                conta,
                dataContratacao,
                tipoAmortiza,
                valorSolicitado,
                taxaJuros,
                taxaMora,
                taxaAtraso,
                numeroParcelas, diaVencimento);

        emprestimoList.add(emprestimo);
        return emprestimo;
    }

    public void listarEmprestimos() {
        for (Emprestimo emprestimo : emprestimoList) {
            emprestimo.consultarEmprestimo();
        }
    }

}
