package com.marktoledo.todolistapi.dto.request;

import com.marktoledo.todolistapi.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTodoRequest {

    @NotNull(message = "Title is required")
    private String title;

    private String description;

    private Date dueDate;


}
