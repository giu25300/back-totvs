package br.com.giuliano.desafio.domain.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
//@JsonIdentityInfo(generator = ObjectIdGenerators.None.class, property = "id", scope = BaseDTO.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RegistroDTO implements Serializable {
    private ClienteDTO cliente;
    private List<TelefoneDTO> telefones;
}
