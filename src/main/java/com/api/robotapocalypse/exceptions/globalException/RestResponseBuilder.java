package com.api.robotapocalypse.exceptions.globalException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RestResponseBuilder {

    private int status;
    private String error;
    private String message;



    public RestResponseBuilder status(HttpStatus status) {
        this.status = status.value();

        if (status.isError()) {
            this.error = status.getReasonPhrase();
        }

        return this;
    }

    public RestResponseBuilder message(String message) {
        this.message = message;
        return this;
    }
    public RestResponse build() {
        RestResponse response = new RestResponse();
        response.setStatus(status);
        response.setError(error);
        response.setMessage(message);
        return response;
    }

    public ResponseEntity<RestResponse> entity() {
        return ResponseEntity.status(status).headers(HttpHeaders.EMPTY).body(build());
    }
}
