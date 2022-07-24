package com.hospitality.menu.functional.steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.hospitality.menu.functional.ResponseContext;
import com.hospitality.menu.functional.ServiceRestClient;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;
import static org.assertj.core.api.Assertions.assertThat;

@Scope(SCOPE_CUCUMBER_GLUE)
public class InfoStepDefinitions {

    @Autowired
    private ServiceRestClient client;

    @Autowired
    private ResponseContext responseContext;

    @And("the application info name is (.*)")
    public void theResponseBodyIs(String expectedApplicationInfoName) {
        JsonNode responseBpdy = responseContext.lastResponseBodyAsJsonNode();
        assertThat(responseBpdy.at("/app/name").asText()).isEqualTo("Hospitality Menu Service");
    }
}
