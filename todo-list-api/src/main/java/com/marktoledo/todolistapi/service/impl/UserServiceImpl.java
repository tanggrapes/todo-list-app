package com.marktoledo.todolistapi.service.impl;

import com.marktoledo.todolistapi.domain.User;
import com.marktoledo.todolistapi.dto.request.SignInRequest;
import com.marktoledo.todolistapi.dto.request.SignUpRequest;
import com.marktoledo.todolistapi.dto.response.AuthenticationResponse;
import com.marktoledo.todolistapi.dto.response.SignUpResponse;
import com.marktoledo.todolistapi.repository.UserRepository;
import com.marktoledo.todolistapi.service.JwtTokenService;
import com.marktoledo.todolistapi.service.UserService;
import com.marktoledo.todolistapi.util.ErrorMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    private JwtTokenService jwtTokenService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {

//      validate if password match
        if (!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtil.PASSWORD_NOT_MATCH);
        }
//        check username
        checkIfUsernameExist(signUpRequest.getUsername());
        User user = User.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .roles("ROLE_USER").build();
        userRepository.save(user);
        return SignUpResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),
                        signInRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            User user = userRepository.getUserByUsername(signInRequest.getUsername());
            return AuthenticationResponse.builder().token(jwtTokenService.createToken(user)).build();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtil.INVALID_CREDENTIALS);
    }

    private void checkIfUsernameExist(String username) {
        User user = userRepository.getUserByUsername(username);

        if (user != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtil.USERNAME_ALREADY_EXIST);
        }

    }


}
