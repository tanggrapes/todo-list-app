package com.marktoledo.todolistapi.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class UpdateTodoRequest {
    private String title;

    private String description;

    private Date dueDate;

    private Boolean isCompleted;
}
