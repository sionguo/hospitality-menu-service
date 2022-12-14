package com.hospitality.menu.health;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
class HealthProbesIntegrationTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void healthLivenessProbeShouldReturnStatusIsUp() throws Exception {
    AvailabilityChangeEvent.publish(
        mockMvc.getDispatcherServlet().getWebApplicationContext(), LivenessState.CORRECT);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/management/health/liveness"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("UP"));
  }

  @Test
  void healthLivenessProbeShouldReturnStatusIsDown() throws Exception {
    AvailabilityChangeEvent.publish(
        mockMvc.getDispatcherServlet().getWebApplicationContext(), LivenessState.BROKEN);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/management/health/liveness"))
        .andExpect(status().isServiceUnavailable())
        .andExpect(jsonPath("$.status").value("DOWN"));
  }

  @Test
  void healthReadinessProbeShouldReturnStatusIsUp() throws Exception {
    AvailabilityChangeEvent.publish(
        mockMvc.getDispatcherServlet().getWebApplicationContext(),
        ReadinessState.ACCEPTING_TRAFFIC);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/management/health/readiness"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("UP"));
  }

  @Test
  void healthReadinessProbeShouldReturnStatusIsOutOfService() throws Exception {
    AvailabilityChangeEvent.publish(
        mockMvc.getDispatcherServlet().getWebApplicationContext(), ReadinessState.REFUSING_TRAFFIC);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/management/health/readiness"))
        .andExpect(status().isServiceUnavailable())
        .andExpect(jsonPath("$.status").value("OUT_OF_SERVICE"));
  }
}
