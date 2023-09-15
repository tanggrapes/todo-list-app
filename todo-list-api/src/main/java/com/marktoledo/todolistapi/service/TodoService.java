package com.marktoledo.todolistapi.service;

import com.marktoledo.todolistapi.dto.request.CreateTodoRequest;
import com.marktoledo.todolistapi.dto.request.UpdateTodoRequest;
import com.marktoledo.todolistapi.dto.response.CreateTodoResponse;
import com.marktoledo.todolistapi.dto.response.TodoResponse;
import com.marktoledo.todolistapi.dto.response.UpdateTodoResponse;

import java.util.List;
import java.util.UUID;

public interface TodoService {

    CreateTodoResponse createTodo(CreateTodoRequest request, UUID userId);

    List<TodoResponse> getTodoList(UUID userId);

    UpdateTodoResponse updateTodo(UUID userId, UUID id, UpdateTodoRequest request);

    void deleteTodo(UUID userId, UUID id);

    TodoResponse getTodo(UUID userId, UUID id);




}
