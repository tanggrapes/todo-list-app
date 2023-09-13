package com.marktoledo.todolistapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponse {


    private UUID id;

    private String username;

    private String firstName;

    private String lastName;
}
