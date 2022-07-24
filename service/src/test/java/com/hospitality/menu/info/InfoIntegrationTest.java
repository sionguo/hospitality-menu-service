package com.hospitality.menu.info;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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

    private final ObjectMapper objectMapper = new ObjectMapper();
    private JsonNode infoBody;

    @BeforeEach
    public void getInfoResponse() throws JsonProcessingException {
        // Given
        RequestEntity<Void> getInfo = RequestEntity.get("http://localhost:" + port + "/actuator/info").build();

        // When
        ResponseEntity<String> response = this.restTemplate.exchange(getInfo, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        infoBody = objectMapper.readTree(response.getBody());
    }

    @Test
    public void infoShouldContainAppName() {
        // Then
        assertThat(infoBody.at("/app/name").asText()).isEqualTo("service");
    }

    @Test
    public void infoShouldContainAppDescription() {
        // Then
        assertThat(infoBody.at("/app/description").asText()).isEqualTo("Hospitality Menu Service " +
                "is a project to manage hospitality services menus such as restaurants, bar, food-truck, etc.");
    }
}
