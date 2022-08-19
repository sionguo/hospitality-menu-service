package com.hospitality.menu.functional.steps;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;
import static org.assertj.core.api.Assertions.assertThat;

import com.hospitality.menu.functional.AppRunner;
import com.hospitality.menu.functional.ResponseContext;
import com.hospitality.menu.functional.ServiceRestClient;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;

@Scope(SCOPE_CUCUMBER_GLUE)
public class AppStepDefinitions {
  @Autowired private ServiceRestClient client;
  @Autowired private AppRunner appRunner;
  @Autowired private ResponseContext responseContext;

  @Given("^the application is running$")
  public void theApplicationIsRunning() {
    assertThat(appRunner.isAppRunning()).isTrue();
  }

  @When("a request is made to {string}")
  public void aRequestIsMadeTo(String relativeURL) {
    responseContext.setLastResponse(client.get(relativeURL));
  }

  @Then("the application returns a response status {int}")
  public void theApplicationReturnsAResponseStatus(int expectedStatusCode) {
    assertThat(responseContext.lastResponse().getStatusCode())
        .isEqualTo(HttpStatus.valueOf(expectedStatusCode));
  }

  @And("the response body is {string}")
  public void theResponseBodyIs(String expectedBody) {
    assertThat(responseContext.lastResponse().getBody()).isEqualTo(expectedBody);
  }
}
