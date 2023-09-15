package com.marktoledo.todolistapi.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class UpdateTodoRequest {

    @NotNull(message = "Title is required")
    private String title;

    private String description;

    private Date dueDate;

    @NotNull(message = "isCompleted cannot be null")
    private Boolean isCompleted;
}
