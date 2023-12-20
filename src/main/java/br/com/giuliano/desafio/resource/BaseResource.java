package br.com.giuliano.desafio.resource;

import br.com.giuliano.desafio.domain.dto.BaseDTO;
import br.com.giuliano.desafio.domain.service.BaseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public abstract class BaseResource<D extends BaseDTO> {
    private final BaseService<D,?> service;

    public BaseResource(BaseService<D,?> service) {
        this.service = service;
    }

//    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public void apagarPorId(@PathVariable("id") Long id) {
//        service.apagarPorId(id);
//    }
}
