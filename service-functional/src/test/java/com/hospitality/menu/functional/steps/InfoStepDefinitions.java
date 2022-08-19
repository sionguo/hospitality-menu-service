package com.hospitality.menu.functional.steps;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.JsonNode;
import com.hospitality.menu.functional.ResponseContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Scope(SCOPE_CUCUMBER_GLUE)
public class InfoStepDefinitions {

  @Autowired private ResponseContext responseContext;

  @Given("the application info contains {string} node")
  public void theApplicationInfoContainsAppNode(String expectedNode) {
    JsonNode responseBody = responseContext.lastResponseBodyAsJsonNode();
    assertThat(responseBody.at("/" + expectedNode).isContainerNode()).isTrue();
  }

  @And("the application info app {string} is {string}")
  public void theApplicationInfoAppMatches(String expectedField, String expectedValue) {
    JsonNode responseBody = responseContext.lastResponseBodyAsJsonNode();
    assertThat(responseBody.at("/app/" + expectedField.replace(".", "/")).asText())
        .isEqualTo(expectedValue);
  }

  @And("the application info app contains version")
  public void theApplicationInfoAppContainsAppVersion() {
    JsonNode responseBody = responseContext.lastResponseBodyAsJsonNode();
    assertThat(responseBody.at("/app/version").asText()).matches("^\\d.\\d.\\d-SNAPSHOT$");
  }

  @And("the application info build {string} is {string}")
  public void theApplicationInfoBuildMatches(String expectedField, String expectedValue) {
    JsonNode responseBody = responseContext.lastResponseBodyAsJsonNode();
    assertThat(responseBody.at("/build/" + expectedField.replace(".", "/")).asText())
        .isEqualTo(expectedValue);
  }

  @And("the application info build contains version")
  public void theApplicationInfoBuildContainsAppVersion() {
    JsonNode responseBody = responseContext.lastResponseBodyAsJsonNode();
    assertThat(responseBody.at("/build/version").asText()).matches("^\\d.\\d.\\d-SNAPSHOT$");
  }

  @And("the application info build contains build time")
  public void theApplicationInfoBuildContainsBuildTime() {
    JsonNode responseBody = responseContext.lastResponseBodyAsJsonNode();
    assertThat(responseBody.at("/build/time").asText())
        .matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(.\\d{3})Z$");
  }
}
