package com.marktoledo.todolistapi.service;

import com.marktoledo.todolistapi.domain.User;
import com.marktoledo.todolistapi.dto.request.SignUpRequest;
import com.marktoledo.todolistapi.dto.response.SignUpResponse;
import com.marktoledo.todolistapi.repository.UserRepository;
import com.marktoledo.todolistapi.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @Test
    void testSignUp(){
        User user = User.builder()
                .firstName("Mark")
                .lastName("Toledo")
                .username("username")
                .password("password")
                .roles("ROLE_USER")
                .build();
        SignUpRequest request = SignUpRequest.builder()
                .firstName("Mark")
                .lastName("Toledo")
                .username("username")
                .password("password")
                .confirmPassword("password")
                .build();
        when(passwordEncoder.encode(Mockito.any(String.class))).thenReturn("");
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        SignUpResponse response = userService.signUp(request);
        Assertions.assertNotNull(response);

    }

    @Test
    void testSignUpExistingUsername(){
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            User user = User.builder()
                    .firstName("Mark")
                    .lastName("Toledo")
                    .username("username")
                    .password("password")
                    .roles("ROLE_USER")
                    .build();
            SignUpRequest request = SignUpRequest.builder()
                    .firstName("Mark")
                    .lastName("Toledo")
                    .username("username")
                    .password("password")
                    .confirmPassword("password")
                    .build();
            when(userRepository.getUserByUsername("username")).thenReturn(user);
            SignUpResponse response = userService.signUp(request);
        });
    }
}
