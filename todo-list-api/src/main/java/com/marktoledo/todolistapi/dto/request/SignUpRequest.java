package com.marktoledo.todolistapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String confirmPassword;
}
