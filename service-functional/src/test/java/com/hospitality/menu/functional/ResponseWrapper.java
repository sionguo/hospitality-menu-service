package com.hospitality.menu.functional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseWrapper {
    private final HttpStatus httpStatus;
    private final String body;

    public ResponseWrapper(ResponseEntity<String> response) {
        this.httpStatus = response.getStatusCode();
        this.body = response.getBody();
    }

    public HttpStatus getStatusCode() {
        return this.httpStatus;
    }

    public String getBody() {
        return this.body;
    }
}
