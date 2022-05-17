package com.hospitality.menu.functional.steps;

import com.hospitality.menu.functional.AppRunner;
import com.hospitality.menu.functional.ServiceRestClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;
import static org.assertj.core.api.Assertions.assertThat;

@Scope(SCOPE_CUCUMBER_GLUE)
public class HealthStepsDefinitions {

    @Autowired
    private ServiceRestClient client;

    @Autowired
    private AppRunner appRunner;

    private String status;

    @Given("^the application is running$")
    public void theApplicationIsRunning() {
        assertThat(appRunner.isAppRunning()).isTrue();
    }

    @When("^a user makes a request to \\/actuator\\/health$")
    public void theUserMakesARequestToTheHealthEndpoint() {
        status = client.getHealth();
    }

    @Then("^the service returns a response body showing status as \"UP\"$")
    public void theServiceReturnsBodyWithStatusAsUP() {
        assertThat(status).isEqualTo("UP");
    }
}
