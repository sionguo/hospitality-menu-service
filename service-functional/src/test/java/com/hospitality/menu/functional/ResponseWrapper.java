package com.hospitality.menu.functional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseWrapper {
    private final HttpStatus httpStatus;
    private final JsonNode bodyAsJsonNode;

    public ResponseWrapper(ResponseEntity<String> response) {
        this.httpStatus = response.getStatusCode();
        try {
            this.bodyAsJsonNode = (new ObjectMapper()).readValue(response.getBody(), JsonNode.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public JsonNode getBodyAsJson() {
        return this.bodyAsJsonNode;
    }
}
