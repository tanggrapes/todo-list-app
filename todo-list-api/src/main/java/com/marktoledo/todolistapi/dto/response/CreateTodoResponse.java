package com.marktoledo.todolistapi.dto.response;

import com.marktoledo.todolistapi.dto.BaseDateDto;
import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
public class CreateTodoResponse extends BaseDateDto {

    private UUID id;

    private String title;

    private String description;

    private Date dueDate;

    private Boolean isCompleted;

    @Builder
    public CreateTodoResponse(Date createdAt, Date updatedAt, UUID id, String title, String description, Date dueDate, Boolean isCompleted) {
        super(createdAt, updatedAt);
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }
}
