package com.marktoledo.todolistapi.service.impl;

import com.marktoledo.todolistapi.domain.Todo;
import com.marktoledo.todolistapi.domain.User;
import com.marktoledo.todolistapi.dto.request.CreateTodoRequest;
import com.marktoledo.todolistapi.dto.request.UpdateTodoRequest;
import com.marktoledo.todolistapi.dto.response.CreateTodoResponse;
import com.marktoledo.todolistapi.dto.response.TodoListResponse;
import com.marktoledo.todolistapi.dto.response.UpdateTodoResponse;
import com.marktoledo.todolistapi.repository.TodoRepository;
import com.marktoledo.todolistapi.repository.UserRepository;
import com.marktoledo.todolistapi.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class TodoServiceImpl implements TodoService {

    private UserRepository userRepository;
    private TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }


    @Override
    public CreateTodoResponse createTodo(CreateTodoRequest request, UUID userId) {

        User user = userRepository.findById(userId).get();
        Todo todo = Todo.builder()
                .dueDate(request.getDueDate())
                .title(request.getTitle())
                .description(request.getDescription())
                .user(user)
                .build();
        return CreateTodoResponse.builder()
                .id(todo.getId())
                .isCompleted(todo.getIsCompleted())
                .dueDate(todo.getDueDate())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .createdAt(todo.getCreatedAt())
                .updatedAt(todo.getUpdatedAt())
                .build();
    }

    @Override
    public List<TodoListResponse> getTodoList(UUID userId) {
        User user = userRepository.findById(userId).get();
        return user.getTodos().stream()
                .map(todo -> TodoListResponse.builder()
                        .id(todo.getId())
                        .isCompleted(todo.getIsCompleted())
                        .dueDate(todo.getDueDate())
                        .title(todo.getTitle())
                        .description(todo.getDescription())
                        .createdAt(todo.getCreatedAt())
                        .updatedAt(todo.getUpdatedAt())
                        .build()
                ).toList();
    }

    @Override
    public UpdateTodoResponse updateTodo(UUID todoId, UpdateTodoRequest request) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not exist"));
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setDueDate(request.getDueDate());
        todo.setIsCompleted(request.getIsCompleted());
        todoRepository.save(todo);
        return UpdateTodoResponse.builder()
                .createdAt(todo.getCreatedAt())
                .updatedAt(todo.getUpdatedAt())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .isCompleted(todo.getIsCompleted())
                .dueDate(todo.getDueDate())
                .build();
    }

    @Override
    public void deleteTodo(UUID todoId) {
        todoRepository.deleteById(todoId);
    }


}
