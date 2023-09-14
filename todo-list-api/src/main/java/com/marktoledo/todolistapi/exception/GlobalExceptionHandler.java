package com.marktoledo.todolistapi.exception;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, List<String>>> constraintViolationException(ConstraintViolationException ex) throws IOException {
//        handle invalid uuid
        Map<String, List<String>> map = new HashMap<>();
        map.put("errors", Arrays.asList(ex.getMessage()));
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

    }

    //    Default for all RuntimeException
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<Map<String, List<String>>> handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex){
        Map<String, List<String>> map = new HashMap<>();
        map.put("errors", Arrays.asList(ex.getMessage()));
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

//    workaround for 403 response
    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<Map<String, List<String>>> handleResponseStatusException(ResponseStatusException ex){
        Map<String, List<String>> map = new HashMap<>();
        map.put("errors", Arrays.asList(ex.getReason()));
        return new ResponseEntity<>(map, HttpStatus.valueOf(ex.getStatusCode().value()));
    }


    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

}

