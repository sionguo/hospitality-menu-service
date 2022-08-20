package com.hospitality.menu.info;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class InfoIntegrationTest {

  private final ObjectMapper objectMapper = new ObjectMapper();
  @LocalServerPort
  private int port;
  @Autowired private TestRestTemplate restTemplate;
  private JsonNode infoBody;

  @BeforeEach
  public void getInfoResponse() throws JsonProcessingException {
    // Given
    RequestEntity<Void> getInfo =
        RequestEntity.get("http://localhost:" + port + "/management/info").build();

    // When
    ResponseEntity<String> response = this.restTemplate.exchange(getInfo, String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    infoBody = objectMapper.readTree(response.getBody());
  }

  @Test
  public void infoShouldContainsTwoSections() {
    // Then
    assertThat(infoBody.size()).isEqualTo(2);
    assertThat(infoBody.get("app")).isNotNull();
    assertThat(infoBody.get("build")).isNotNull();
  }

  @Test
  public void infoShouldContainAppSectionWithExpectedNumberOfFields() {
    // Then
    assertThat(infoBody.get("app").isContainerNode()).isTrue();
    assertThat(infoBody.get("app").size()).isEqualTo(4);
  }

  @Test
  public void infoShouldContainBuildSectionWithExpectedNumberOfFields() {
    // Then
    assertThat(infoBody.get("build").isContainerNode()).isTrue();
    assertThat(infoBody.get("build").size()).isEqualTo(5);
  }

  @Test
  public void infoShouldContainAppName() {
    // Then
    assertThat(infoBody.at("/app/name").asText()).isEqualTo("service");
  }

  @Test
  public void infoShouldContainAppDescription() {
    // Then
    assertThat(infoBody.at("/app/description").asText())
        .isEqualTo(
            "Hospitality Menu Service "
                + "is a project to manage hospitality services menus such as restaurants, "
                + "bar, food-truck, etc.");
  }

  @Test
  public void infoShouldContainAppJavaVersion() {
    // Then
    assertThat(infoBody.at("/app/java/version").asText()).isEqualTo("17");
  }

  @Test
  public void infoShouldContainAppVersion() {
    // Then
    assertThat(infoBody.at("/app/version").asText()).matches("^\\d.\\d.\\d-SNAPSHOT$");
  }

  @Test
  public void infoShouldContainBuildName() {
    // Then
    assertThat(infoBody.at("/build/name").asText()).isEqualTo("service");
  }

  @Test
  public void infoShouldContainBuildGroup() {
    // Then
    assertThat(infoBody.at("/build/group").asText()).isEqualTo("com.hospitality.menu");
  }

  @Test
  public void infoShouldContainBuildArtifact() {
    // Then
    assertThat(infoBody.at("/build/artifact").asText()).isEqualTo("service");
  }

  @Test
  public void infoShouldContainBuildVersion() {
    // Then
    assertThat(infoBody.at("/build/version").asText()).matches("^\\d.\\d.\\d-SNAPSHOT$");
  }

  @Test
  public void infoShouldContainBuildTime() {
    // Then
    assertThat(infoBody.at("/build/time").asText())
        .matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(.\\d{3})Z$");
  }

  @Test
  public void infoShouldContainAppVersionSameAsBuildVersion() {
    // Then
    assertThat(infoBody.at("/app/version").asText())
        .isEqualTo(infoBody.at("/build/version").asText());
  }
}
