package com.marktoledo.todolistapi.service;

import com.marktoledo.todolistapi.dto.request.CreateTodoRequest;
import com.marktoledo.todolistapi.dto.request.UpdateTodoRequest;
import com.marktoledo.todolistapi.dto.response.CreateTodoResponse;
import com.marktoledo.todolistapi.dto.response.TodoListResponse;
import com.marktoledo.todolistapi.dto.response.UpdateTodoResponse;

import java.util.List;
import java.util.UUID;

public interface TodoService {

    CreateTodoResponse createTodo(CreateTodoRequest request, UUID userId);

    List<TodoListResponse> getTodoList(UUID userId);

    UpdateTodoResponse updateTodo(UUID todoId, UpdateTodoRequest request);

    void deleteTodo(UUID todoId);




}
