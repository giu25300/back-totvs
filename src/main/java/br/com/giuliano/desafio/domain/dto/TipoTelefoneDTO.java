package br.com.giuliano.desafio.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoTelefoneDTO extends BaseDTO {
    private Integer id;
    private Integer nome;
}
