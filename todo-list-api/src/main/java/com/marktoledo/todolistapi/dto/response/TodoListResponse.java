package com.marktoledo.todolistapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoListResponse {

    private UUID id;

    private String title;

    private String description;

    private Date dueDate;

    private Boolean isCompleted;
}
