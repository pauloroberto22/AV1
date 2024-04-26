package com.example.ibge.service;

import com.example.ibge.model.IbgeEntity;
import com.example.ibge.repository.IbgeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IbgeService {

    @Autowired
    private IbgeRepository ibgeRepository;

    public String consultarURL(String apiURL) {
        String dados = "";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiURL, String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            dados = responseEntity.getBody();
            System.out.println(dados);
        } else {
            dados = "Falha ao obter dados, Código do status: " + responseEntity.getStatusCode();
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.readTree(dados);
            for (JsonNode node : jsonNode) {
                String codigo = node.get("codigo").asText();
                String nome = node.get("nome").asText();


                IbgeEntity ibgeEntity = new IbgeEntity();

                inserir(ibgeEntity);
            }



        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return dados;
    }
    public IbgeEntity inserir(IbgeEntity ibge ) {
        return ibgeRepository.save(ibge);
    }

    public String consultarCompleto(int completo){ return consultarURL("https://servicodados.ibge.gov.br/api/v3/noticias" + completo + "/noticiaserelases" ); }

    public String consultarNoticias(int completo, int noticias){
        return consultarURL("https://servicodados.ibge.gov.br/api/v3/noticias" + completo + "/noticia" + noticias);
    }
    public String consultarReleases(int completo, int releases){
        return consultarURL("https://servicodados.ibge.gov.br/api/v3/noticias" + completo + "/release" + releases);
    }

}
