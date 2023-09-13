package com.marktoledo.todolistapi.service.impl;

import com.marktoledo.todolistapi.domain.Todo;
import com.marktoledo.todolistapi.domain.User;
import com.marktoledo.todolistapi.dto.request.CreateTodoRequest;
import com.marktoledo.todolistapi.dto.response.CreateTodoResponse;
import com.marktoledo.todolistapi.repository.TodoRepository;
import com.marktoledo.todolistapi.repository.UserRepository;
import com.marktoledo.todolistapi.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TodoServiceImpl implements TodoService {

    private UserRepository userRepository;
    private TodoRepository todoRepository;
    @Autowired
    public TodoServiceImpl(UserRepository userRepository, TodoRepository todoRepository){
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }


    @Override
    public CreateTodoResponse createTodo(CreateTodoRequest request, UUID userId) {
        User user = userRepository.getReferenceById(userId);
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
                .build();
    }
}
