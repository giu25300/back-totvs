package br.com.giuliano.desafio.resource;

import br.com.giuliano.desafio.core.entity.Cliente;
import br.com.giuliano.desafio.core.entity.Telefone;
import br.com.giuliano.desafio.domain.dto.TelefoneDTO;
import br.com.giuliano.desafio.domain.service.ClienteService;
import br.com.giuliano.desafio.domain.service.TelefoneService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/telefones", path = "/telefones")
public class TelefoneResource {
    @Autowired
    private TelefoneService service;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TelefoneDTO> buscarPeloID(@PathVariable("id") Long id) throws JsonProcessingException {
        Optional<TelefoneDTO> optionalT = Optional.ofNullable(service.buscarPeloId(id));

        return optionalT.map(t -> new ResponseEntity<>(t, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping(path = "/buscarpelonumero/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TelefoneDTO>> buscarPeloNumero(@PathVariable("numero") String numero) throws JsonProcessingException {
        List<TelefoneDTO> lista = service.buscarPeloNumero(numero);
        return lista.isEmpty()? new ResponseEntity<>(HttpStatus.NO_CONTENT): new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
