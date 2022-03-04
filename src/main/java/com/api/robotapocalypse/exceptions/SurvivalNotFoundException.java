package com.api.robotapocalypse.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SurvivalNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8085333387338874650L;
	public SurvivalNotFoundException() {
        super();
    }
    public SurvivalNotFoundException(String message) {
        super(message);
    }


}