package com.marktoledo.todolistapi.controller;

import com.marktoledo.todolistapi.dto.request.SignUpRequest;
import com.marktoledo.todolistapi.dto.response.SignUpResponse;
import com.marktoledo.todolistapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(path = "/sign-up",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest request){
        return new ResponseEntity<>(this.userService.signUp(request), HttpStatus.CREATED);
    }
}
