package br.com.giuliano.desafio.domain.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIdentityInfo(generator = ObjectIdGenerators.None.class, property = "id", scope = BaseDTO.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include()
    private Integer id;

    public BaseDTO() {}

    public BaseDTO(Integer id) {
        this.id = id;
    }
}
