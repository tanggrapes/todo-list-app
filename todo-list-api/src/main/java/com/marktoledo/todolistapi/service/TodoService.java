package com.marktoledo.todolistapi.service;

import com.marktoledo.todolistapi.dto.request.CreateTodoRequest;
import com.marktoledo.todolistapi.dto.response.CreateTodoResponse;
import com.marktoledo.todolistapi.dto.response.TodoListResponse;

import java.util.List;
import java.util.UUID;

public interface TodoService {

    CreateTodoResponse createTodo(CreateTodoRequest request, UUID userId);

    List<TodoListResponse> getTodoList(UUID userId);




}
