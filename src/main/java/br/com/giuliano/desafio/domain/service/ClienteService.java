package br.com.giuliano.desafio.domain.service;

import br.com.giuliano.desafio.core.entity.Cliente;
import br.com.giuliano.desafio.core.entity.Telefone;
import br.com.giuliano.desafio.domain.dto.ClienteDTO;
import br.com.giuliano.desafio.domain.dto.TelefoneDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Objects;

@Service
public class ClienteService extends BaseService<ClienteDTO,Cliente> {
    @Autowired
    private TelefoneService telefoneService;
    private String url;
    public ClienteService() {
        super(ClienteDTO.class, Cliente.class);
    }

    @PostConstruct
    public void ler() {
        this.url = String.format("%s/clientes", this.baseUrl);
    }

    public ClienteDTO buscarPeloId(Long id) throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(String.format("%s/%d", this.url, id), String.class);
        Cliente cliente = this.objectMapper.readValue(response.getBody(), Cliente.class);
        return this.mapToDTO(cliente,this.getDTOClass());
    }

    public ClienteDTO inserir(ClienteDTO cliente, List<TelefoneDTO> listaTelefones) throws JsonProcessingException {
        Cliente auxCliente = this.mapFromDTO(cliente,Cliente.class);
        validaInsercao(cliente,listaTelefones);
        String jsonStr = this.objectMapper.writeValueAsString(auxCliente);
        HttpEntity<String> request = new HttpEntity<String>(jsonStr, this.httpHeaders);
        Cliente out =  restTemplate.postForObject(this.url, request, Cliente.class);
        return this.mapToDTO(out,this.getDTOClass());
    }

    public ClienteDTO buscarPeloNome(String nome) throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(String.format("%s?nome=%s", this.url, nome), String.class);
        final List<Cliente> asList = this.objectMapper.readValue(response.getBody(), new TypeReference<List<Cliente>>() {
        });
        return this.mapToDTO(asList.get(0),this.getDTOClass());
    }

    public List<Cliente> buscarAproximadoPeloNome(String nome) throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(String.format("%s?nome_like=%s", this.url, nome), String.class);
        final List<Cliente> listaCliente = this.objectMapper.readValue(response.getBody(), new TypeReference<List<Cliente>>() {
        });
        return listaCliente;
    }
    private boolean validaInsercao(ClienteDTO cliente, List<TelefoneDTO> listaTelefones) throws JsonProcessingException {
        ClienteDTO auxCliente = this.buscarPeloNome(cliente.getNome());
        if (!Objects.isNull(auxCliente)){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400),"Já existe um cliente com esse nome");
        }
        for(TelefoneDTO t: listaTelefones){
            List<TelefoneDTO> listaTelefone = telefoneService.buscarPeloNumero(t.getNumero());
            if (!listaTelefone.isEmpty()){
                throw new HttpClientErrorException(HttpStatusCode.valueOf(400),"Este número já está cadastrado para outro cliente");
            }
        }

        return true;
    }
}