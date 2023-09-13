package com.marktoledo.todolistapi.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTodoResponse {

    private String title;

    private String description;

    private Date dueDate;

    private Boolean isCompleted;
}
