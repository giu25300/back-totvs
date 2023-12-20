package br.com.giuliano.desafio.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private Integer id;

    @NotNull
    @Size(min=2, max=10)
    private String nome;

    @NotNull
    @Size(min=2, max=100)
    private String endereco;

    @NotNull
    @Size(min=2, max=100)
    private String bairro;
}
