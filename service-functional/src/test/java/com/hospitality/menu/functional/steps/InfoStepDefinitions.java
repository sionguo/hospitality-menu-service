package com.hospitality.menu.functional.steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.hospitality.menu.functional.ResponseContext;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;
import static org.assertj.core.api.Assertions.assertThat;

@Scope(SCOPE_CUCUMBER_GLUE)
public class InfoStepDefinitions {

    @Autowired
    private ResponseContext responseContext;

    @And("the application info app {string} is {string}")
    public void theApplicationInfoAppMatches(String expectedField, String expectedValue) {
        JsonNode responseBody = responseContext.lastResponseBodyAsJsonNode();
        assertThat(responseBody.at("/app/" + expectedField).asText()).isEqualTo(expectedValue);
    }
}
