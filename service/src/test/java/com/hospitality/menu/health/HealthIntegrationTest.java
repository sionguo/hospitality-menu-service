package com.hospitality.menu.health;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@SuppressWarnings({"PMD.UnusedLocalVariable", "PMD.UnusedPrivateMethod"})
class HealthIntegrationTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Test
  void healthShouldReturnHttpStatusOk() {
    // Given
    RequestEntity<Void> getHealth =
        RequestEntity.get("http://localhost:" + port + "/management/health").build();

    // When
    ResponseEntity<String> response = this.restTemplate.exchange(getHealth, String.class);

    // Then
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void healthShouldReturnResponseBodyStatusIsUp() {
    // Given
    RequestEntity<Void> getHealth =
        RequestEntity.get("http://localhost:" + port + "/management/health").build();

    // When
    ResponseEntity<String> response = this.restTemplate.exchange(getHealth, String.class);

    // Then
    assertThat(response.getBody())
        .isEqualTo("{\"status\":\"UP\",\"groups\":[\"liveness\",\"readiness\"]}");
  }
}
