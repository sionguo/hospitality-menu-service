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
class InfoIntegrationTest {

  private final ObjectMapper objectMapper = new ObjectMapper();
  @LocalServerPort private int port;
  @Autowired private TestRestTemplate restTemplate;
  private JsonNode infoBody;

  @BeforeEach
  void getInfoResponse() throws JsonProcessingException {
    // Given
    RequestEntity<Void> getInfo =
        RequestEntity.get("http://localhost:" + port + "/management/info").build();

    // When
    ResponseEntity<String> response = this.restTemplate.exchange(getInfo, String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    infoBody = objectMapper.readTree(response.getBody());
  }

  @Test
  void infoShouldContainThreeSections() {
    // Then
    assertThat(infoBody.size()).isEqualTo(3);
  }

  @Test
  void infoShouldContainAppSection() {
    // Then
    assertThat(infoBody.get("app")).isNotNull();
  }

  @Test
  void infoShouldContainBuildSection() {
    // Then
    assertThat(infoBody.get("build")).isNotNull();
  }

  @Test
  void infoShouldContainGitSection() {
    // Then
    assertThat(infoBody.get("git")).isNotNull();
  }

  @Test
  void infoShouldContainAppSectionWithExpectedNumberOfFields() {
    // Then
    assertThat(infoBody.get("app").size()).isEqualTo(4);
  }

  @Test
  void infoShouldContainBuildSectionWithExpectedNumberOfFields() {
    // Then
    assertThat(infoBody.get("build").size()).isEqualTo(5);
  }

  @Test
  void infoShouldContainGitSectionWithExpectedNumberOfFields() {
    // Then
    assertThat(infoBody.get("git").size()).isEqualTo(2);
  }

  @Test
  void infoShouldContainAppName() {
    // Then
    assertThat(infoBody.at("/app/name").asText()).isEqualTo("service");
  }

  @Test
  void infoShouldContainAppDescription() {
    // Then
    assertThat(infoBody.at("/app/description").asText())
        .isEqualTo(
            "Hospitality Menu Service "
                + "is a project to manage hospitality services menus such as restaurants, "
                + "bar, food-truck, etc.");
  }

  @Test
  void infoShouldContainAppJavaVersion() {
    // Then
    assertThat(infoBody.at("/app/java/version").asText()).isEqualTo("17");
  }

  @Test
  void infoShouldContainAppVersion() {
    // Then
    assertThat(infoBody.at("/app/version").asText()).matches("^\\d.\\d.\\d(-SNAPSHOT)?$");
  }

  @Test
  void infoShouldContainBuildName() {
    // Then
    assertThat(infoBody.at("/build/name").asText()).isEqualTo("service");
  }

  @Test
  void infoShouldContainBuildGroup() {
    // Then
    assertThat(infoBody.at("/build/group").asText()).isEqualTo("com.hospitality.menu");
  }

  @Test
  void infoShouldContainBuildArtifact() {
    // Then
    assertThat(infoBody.at("/build/artifact").asText()).isEqualTo("service");
  }

  @Test
  void infoShouldContainBuildVersion() {
    // Then
    assertThat(infoBody.at("/build/version").asText()).matches("^\\d.\\d.\\d(-SNAPSHOT)?$");
  }

  @Test
  void infoShouldContainBuildTime() {
    // Then
    assertThat(infoBody.at("/build/time").asText())
        .matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(.\\d{3})Z$");
  }

  @Test
  void infoShouldContainGitBranch() {
    // Then
    assertThat(infoBody.at("/git/branch").asText()).isEqualTo("main");
  }

  @Test
  void infoShouldContainGitCommitSection() {
    // Then
    assertThat(infoBody.at("/git/commit").size()).isEqualTo(2);
  }

  @Test
  void infoShouldContainGitCommitIdSection() {
    // Then
    assertThat(infoBody.at("/git/commit/id")).isNotNull();
  }

  @Test
  void infoShouldContainGitCommitTimeSection() {
    // Then
    assertThat(infoBody.at("/git/commit/time")).isNotNull();
  }

  @Test
  void infoShouldContainGitCommitShortId() {
    // Then
    assertThat(infoBody.at("/git/commit/id").asText()).matches("^\\w{7}$");
  }

  @Test
  void infoShouldContainGitCommitTime() {
    // Then
    assertThat(infoBody.at("/git/commit/time").asText())
        .matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z$");
  }
}
