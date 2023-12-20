package br.com.giuliano.desafio.resource;

import br.com.giuliano.desafio.core.entity.Cliente;
import br.com.giuliano.desafio.domain.dto.ClienteDTO;
import br.com.giuliano.desafio.domain.dto.RegistroDTO;
import br.com.giuliano.desafio.domain.dto.TelefoneDTO;
import br.com.giuliano.desafio.domain.service.ClienteService;
import br.com.giuliano.desafio.domain.service.TelefoneService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clientes", path = "/clientes")
public class ClienteResource {
    @Autowired
    private ClienteService service;

    @Autowired
    private TelefoneService telefoneService;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDTO> buscarPeloID(@PathVariable("id") Long id) throws JsonProcessingException {

        Optional<ClienteDTO> optionalT = Optional.ofNullable(service.buscarPeloId(id));

        return optionalT.map(t -> new ResponseEntity<>(t, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PostMapping("/inserir")
    public ResponseEntity<ClienteDTO> inserir(@RequestBody RegistroDTO registro) throws JsonProcessingException {
        ClienteDTO cliente = service.inserir(registro.getCliente(),registro.getTelefones());
        List<TelefoneDTO> listaTelefones = this.telefoneService.inserir(registro.getTelefones(),cliente.getId());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @GetMapping(path = "/buscarpelonome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDTO> buscarPeloNome(@PathVariable("nome") String nome) throws JsonProcessingException {

        Optional<ClienteDTO> optionalT = Optional.ofNullable((ClienteDTO) service.buscarPeloNome(nome));

        return optionalT.map(t -> new ResponseEntity<>(t, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }
}


