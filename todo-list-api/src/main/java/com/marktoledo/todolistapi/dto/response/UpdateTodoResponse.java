package com.marktoledo.todolistapi.dto.response;


import com.marktoledo.todolistapi.dto.BaseDateDto;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class UpdateTodoResponse extends BaseDateDto {

    private String title;

    private String description;

    private Date dueDate;

    private Boolean isCompleted;

    @Builder
    public UpdateTodoResponse(Date createdAt, Date updatedAt, String title, String description, Date dueDate, Boolean isCompleted) {
        super(createdAt, updatedAt);
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }
}
