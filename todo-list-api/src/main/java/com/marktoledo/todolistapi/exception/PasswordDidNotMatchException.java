package com.marktoledo.todolistapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PasswordDidNotMatchException extends RuntimeException {

    public PasswordDidNotMatchException(String message) {
        super(message);
    }
}