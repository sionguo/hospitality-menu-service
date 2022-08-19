package com.hospitality.menu.functional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseContext {
  private ResponseWrapper lastResponse;

  @Autowired private ObjectMapper objectMapper;

  public ResponseWrapper lastResponse() {
    return lastResponse;
  }

  public JsonNode lastResponseBodyAsJsonNode() {
    try {
      return objectMapper.readTree(lastResponse.getBody());
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public void setLastResponse(ResponseWrapper lastResponse) {
    this.lastResponse = lastResponse;
  }
}
