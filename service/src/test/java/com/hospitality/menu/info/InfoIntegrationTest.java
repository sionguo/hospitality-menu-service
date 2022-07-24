package com.hospitality.menu.info;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class InfoIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void infoShouldReturnAppName() throws JsonProcessingException {
        // Given
        RequestEntity<Void> getInfo = RequestEntity.get("http://localhost:" + port + "/actuator/info").build();

        // When
        ResponseEntity<String> response = this.restTemplate.exchange(getInfo, String.class);
        JsonNode body = objectMapper.readTree(response.getBody());
        System.out.println("response = " + response.getBody());
        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(body.at("/app/name").asText()).isEqualTo("Hospitality Menu Service");
    }
}
