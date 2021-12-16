package com.rcfin.models;

public class Endereco {

    public enum tipoEndereco {RESIDENCIAL, COMERCIAL, OUTRO}

    private tipoEndereco tipo;
    private String rua;
    private String numero;
    private String complemento;
    private String cep;
    private String estado;
    private String cidade;

    public Endereco(tipoEndereco tipo, String rua, String numero,
                    String complemento, String cep, String estado,
                    String cidade) {
        this.tipo = tipo;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
    }
}
