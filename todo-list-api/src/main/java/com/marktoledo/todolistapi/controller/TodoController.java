package com.marktoledo.todolistapi.controller;

import com.marktoledo.todolistapi.dto.request.CreateTodoRequest;
import com.marktoledo.todolistapi.dto.response.CreateTodoResponse;
import com.marktoledo.todolistapi.service.JwtTokenService;
import com.marktoledo.todolistapi.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/todos")
@Validated
public class TodoController {

    private TodoService todoService;

    private JwtTokenService jwtTokenService;

    @Autowired
    public TodoController(TodoService todoService, JwtTokenService jwtTokenService) {
        this.todoService = todoService;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping
    public ResponseEntity<CreateTodoResponse> createTodo(@Valid @RequestBody CreateTodoRequest request,
                                                         @RequestHeader(name = "Authorization") String token) {
        return new ResponseEntity<>(this.todoService.createTodo(request, jwtTokenService.getUserIdInToken(token)), HttpStatus.CREATED);
    }
}
