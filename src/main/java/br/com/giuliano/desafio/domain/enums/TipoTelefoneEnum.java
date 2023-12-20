package br.com.giuliano.desafio.domain.enums;

public enum TipoTelefoneEnum {
    CELULAR(1, "CELULAR"), RESIDENCIAL(2, "RESIDENCIAL");
    private Integer tipo;
    private String nome;

    private TipoTelefoneEnum(Integer tipo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
    }
}