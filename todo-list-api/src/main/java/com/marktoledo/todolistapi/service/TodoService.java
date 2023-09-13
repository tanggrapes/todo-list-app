package com.marktoledo.todolistapi.service;

import com.marktoledo.todolistapi.dto.request.CreateTodoRequest;
import com.marktoledo.todolistapi.dto.response.CreateTodoResponse;

import java.util.UUID;

public interface TodoService {

    CreateTodoResponse createTodo(CreateTodoRequest request, UUID userId);


}
