package com.marktoledo.todolistapi.controller;

import com.marktoledo.todolistapi.dto.request.SignUpRequest;
import com.marktoledo.todolistapi.dto.response.SignUpResponse;
import com.marktoledo.todolistapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(path = "/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest request){
        return new ResponseEntity<>(this.userService.signUp(request), HttpStatus.CREATED);
    }
}
