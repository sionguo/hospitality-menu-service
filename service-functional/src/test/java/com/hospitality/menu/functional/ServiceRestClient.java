package com.hospitality.menu.functional;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class ServiceRestClient {

    private final RestTemplate restTemplate = new RestTemplateBuilder().build();

    public ResponseWrapper healthCheckRequest() {
        try {
            RequestEntity<Void> getHealth = RequestEntity.get(URI.create("http://localhost:8080/actuator/health")).build();
            return new ResponseWrapper(restTemplate.exchange(getHealth, String.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
