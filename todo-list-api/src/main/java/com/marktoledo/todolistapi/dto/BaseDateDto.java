package com.marktoledo.todolistapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@AllArgsConstructor
public class BaseDateDto {

    private Date createdAt;

    private Date updatedAt;
}
