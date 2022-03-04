package com.api.robotapocalypse.exceptions.globalException;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RestResponse {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String error;
    private String message;
    public static RestResponseBuilder builder() {
        return new RestResponseBuilder();
    }

}
