package com.marktoledo.todolistapi.service;

import com.marktoledo.todolistapi.dto.request.SignInRequest;
import com.marktoledo.todolistapi.dto.request.SignUpRequest;
import com.marktoledo.todolistapi.dto.response.AuthenticationResponse;
import com.marktoledo.todolistapi.dto.response.SignUpResponse;

public interface UserService {

    SignUpResponse signUp(SignUpRequest signUpRequest);

    AuthenticationResponse signIn(SignInRequest signInRequest);

}
