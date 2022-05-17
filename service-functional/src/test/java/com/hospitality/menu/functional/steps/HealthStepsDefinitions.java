package com.hospitality.menu.functional.steps;

import com.hospitality.menu.functional.AppRunner;
import com.hospitality.menu.functional.ResponseWrapper;
import com.hospitality.menu.functional.ServiceRestClient;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;
import static org.assertj.core.api.Assertions.assertThat;

@Scope(SCOPE_CUCUMBER_GLUE)
public class HealthStepsDefinitions {

    @Autowired
    private ServiceRestClient client;

    @Autowired
    private AppRunner appRunner;

    private ResponseWrapper response;

    @Given("^the application is running$")
    public void theApplicationIsRunning() {
        assertThat(appRunner.isAppRunning()).isTrue();
    }

    @When("^a user makes a request to \\/actuator\\/health$")
    public void theUserMakesARequestToTheHealthEndpoint() {
        response = client.healthCheckRequest();
    }

    @Then("the service returns a {int} status response")
    public void theServiceReturnsAStatusResponse(int expectedStatusCodeValue) {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(expectedStatusCodeValue));
    }

    @And("the response has a body showing application status as {string}")
    public void theResponseHasABodyShowingApplicationStatusAs(String expectedBodyStatusFieldValue) {
        assertThat(response.getBodyAsJson().get("status").asText()).isEqualTo(expectedBodyStatusFieldValue);
    }
}
