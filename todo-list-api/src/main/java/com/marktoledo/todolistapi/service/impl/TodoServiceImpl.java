package com.marktoledo.todolistapi.service.impl;

import com.marktoledo.todolistapi.domain.Todo;
import com.marktoledo.todolistapi.domain.User;
import com.marktoledo.todolistapi.dto.request.CreateTodoRequest;
import com.marktoledo.todolistapi.dto.request.UpdateTodoRequest;
import com.marktoledo.todolistapi.dto.response.CreateTodoResponse;
import com.marktoledo.todolistapi.dto.response.TodoResponse;
import com.marktoledo.todolistapi.dto.response.UpdateTodoResponse;
import com.marktoledo.todolistapi.repository.TodoRepository;
import com.marktoledo.todolistapi.repository.UserRepository;
import com.marktoledo.todolistapi.service.TodoService;
import com.marktoledo.todolistapi.util.ErrorMessageUtil;
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
//      token is valid but user not exist
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessageUtil.INVALID_USER));
        Todo todo = Todo.builder()
                .dueDate(request.getDueDate())
                .title(request.getTitle())
                .description(request.getDescription())
                .user(user)
                .build();
        todoRepository.save(todo);
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
    public List<TodoResponse> getTodoList(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessageUtil.INVALID_USER));
        return user.getTodos().stream()
                .map(todo -> TodoResponse.builder()
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

    //  limitation and scope of this update is need include all properties in payload even if not updated
//    if its null it will update to null except to required
    @Override
    public UpdateTodoResponse updateTodo(UUID userId, UUID id, UpdateTodoRequest request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtil.TODO_NOT_FOUND));

        if (!userId.toString().equals(todo.getUser().getId().toString())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessageUtil.UNAUTHORIZED_ACCESS);
        }
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
    public void deleteTodo(UUID userId, UUID id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtil.TODO_NOT_FOUND));
        validateUser(todo,userId);
        todoRepository.delete(todo);
    }


    @Override
    public TodoResponse getTodo(UUID userId, UUID id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtil.TODO_NOT_FOUND));

        validateUser(todo,userId);

        return TodoResponse.builder()
                .id(todo.getId())
                .isCompleted(todo.getIsCompleted())
                .dueDate(todo.getDueDate())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .createdAt(todo.getCreatedAt())
                .updatedAt(todo.getUpdatedAt())
                .build();
    }

    private void validateUser(Todo todo, UUID userId){
        if (!todo.getUser().getId().toString().equals(userId.toString())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessageUtil.UNAUTHORIZED_ACCESS);
        }
    }


}
