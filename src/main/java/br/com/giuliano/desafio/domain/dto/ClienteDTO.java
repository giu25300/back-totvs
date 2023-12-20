package br.com.giuliano.desafio.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO extends BaseDTO {
    private String nome;
    private String endereco;
    private String bairro;
}
