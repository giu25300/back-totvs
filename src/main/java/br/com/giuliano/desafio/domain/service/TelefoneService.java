package br.com.giuliano.desafio.domain.service;

import br.com.giuliano.desafio.core.entity.Telefone;
import br.com.giuliano.desafio.domain.dto.TelefoneDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
@Service
public class TelefoneService extends BaseService<TelefoneDTO,Telefone> {
    private String url;
    public TelefoneService() {
        super(TelefoneDTO.class, Telefone.class);
    }

    @PostConstruct
    public void ler() {
        this.url = String.format("%s/telefones", this.baseUrl);
    }

    public TelefoneDTO buscarPeloId(Long id) throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(String.format("%s/%d", this.url, id), String.class);
        Telefone telefone = this.objectMapper.readValue(response.getBody().toString(), Telefone.class);
        return this.mapToDTO(telefone,TelefoneDTO.class);
    }

    /***
     * Busca os registros de um numero de telefone
     * @param numero
     * @return Lista de TelefoneDTO
     * @throws JsonProcessingException
     */
    public List<TelefoneDTO> buscarPeloNumero(String numero) throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(String.format("%s?numero=%s", this.url, numero), String.class);
        final List<Telefone> asList = this.objectMapper.readValue(response.getBody(), new TypeReference<List<Telefone>>() {
        });

        return this.mapAllToDTO(asList,this.getDTOClass());
    }

    /***
     * Valida se um telefone é válido
     * @param telefone
     * @return true ou exception 400
     */
    private boolean valida(Telefone telefone){
        if (telefone.getNumero().isEmpty()){
            //telefone não pode ser nulo
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400),"Telefone inválido");
        }
        return true;
    }

    /***
     * Insere uma lista de telefones de clientes na base
     * @param listaTelefones
     * @param clienteId
     * @return Lista de telefones inseridos com o respectivo ID
     * @throws JsonProcessingException
     */
    public List<TelefoneDTO> inserir(List<TelefoneDTO> listaTelefones, Integer clienteId) throws JsonProcessingException {
        List<TelefoneDTO> saida= new ArrayList<>();

        for(TelefoneDTO t: listaTelefones){
            t.setClienteId(clienteId);
            Telefone telefone = this.mapFromDTO(t,Telefone.class);
            valida(telefone);
            String jsonStr = this.objectMapper.writeValueAsString(telefone);
            HttpEntity<String> request = new HttpEntity<String>(jsonStr, this.httpHeaders);
            Telefone out =  restTemplate.postForObject(this.url, request, Telefone.class);
            saida.add(this.mapToDTO(out,TelefoneDTO.class));
        }
        return saida;
    }
}
