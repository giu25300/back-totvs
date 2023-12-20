package br.com.giuliano.desafio.domain.service;

import br.com.giuliano.desafio.domain.dto.BaseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class BaseService<D extends BaseDTO,T> implements IBaseService<D, T>  {
    @Value("${json.url.base}")
    protected String baseUrl;
    protected RestTemplate restTemplate;
    protected HttpHeaders httpHeaders;
    protected ObjectMapper objectMapper;
    private final Class<D> dtoClass;
    private final Class<T> entityClass;
    public BaseService(Class<D> dtoClass, Class<T> entityClass) {
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;

        this.httpHeaders = new HttpHeaders();
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
        this.httpHeaders.set("Content-type", "application/json;charset=UTF-8");
    }
    public Class<D> getDTOClass() {
        return this.dtoClass;
    }

    public Class<T> getEntityClass() {
        return this.entityClass;
    }

//    public String buscarPeloId(Long id) {
//        try {
//            ResponseEntity<String> responseEntity = restTemplate.getForEntity(String.format("%s/%d", this.baseUrl), String.class);
//            return (responseEntity.getStatusCode().value() == 200 ? responseEntity.getBody() : "");
//        } catch (HttpClientErrorException e400) {
//            throw new RuntimeException("" + e400.getMessage());
//        } catch (HttpServerErrorException e500) {
//            throw new RuntimeException("" + e500.getMessage());
//        }
//    }

//    public String gravar(D d) {
//        HttpEntity<String> request = new HttpEntity<String>(d.toString(), httpHeaders);
//
//        String personResultAsJsonStr = restTemplate.postForObject(this.baseUrl, request, String.class);
//        return personResultAsJsonStr;
//    }

//    public String atualizar(D d) {
//        try {
//            restTemplate.put(String.format("%s/%d", this.baseUrl), d);
//
//            return this.buscarPeloId(d.getId());
//        } catch (HttpClientErrorException e400) {
//            throw new RuntimeException("" + e400.getMessage());
//        } catch (HttpServerErrorException e500) {
//            throw new RuntimeException("" + e500.getMessage());
//        }
//    }

//    public void apagarPorId(Long id) {
//        try {
//            restTemplate.delete(String.format("%s/%d", this.baseUrl, id));
//        } catch (HttpClientErrorException e400) {
//            throw new RuntimeException("" + e400.getMessage());
//        } catch (HttpServerErrorException e500) {
//            throw new RuntimeException("" + e500.getMessage());
//        }
//    }
}