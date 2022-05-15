package com.hospitality.menu.functional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class ServiceRestClient {

    private final RestTemplate restTemplate = new RestTemplateBuilder().build();

    public String getHealth() {
        try {
            RequestEntity<Void> getHealth = RequestEntity.get(URI.create("http://localhost:8080/actuator/health")).build();
            ResponseEntity<String> response = restTemplate.exchange(getHealth, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode statusResponse = objectMapper.readValue(response.getBody(), JsonNode.class);
            return statusResponse.get("status").asText();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
