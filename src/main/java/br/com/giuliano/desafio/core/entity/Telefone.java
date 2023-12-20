package br.com.giuliano.desafio.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Telefone {
    private Integer id;
    //private Integer tipoTelefone;

    @NotNull(message = "O número do telefone é obrigatório")
    @Pattern(regexp="\\d{10,11}")
    private String numero;

    @NotNull(message = "O cliente é obrigatório")
    private Integer clienteId;
}
