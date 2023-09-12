package com.marktoledo.todolistapi.service.impl;

import com.marktoledo.todolistapi.domain.User;
import com.marktoledo.todolistapi.dto.request.SignUpRequest;
import com.marktoledo.todolistapi.dto.response.AuthenticationResponse;
import com.marktoledo.todolistapi.dto.response.SignUpResponse;
import com.marktoledo.todolistapi.exception.PasswordDidNotMatchException;
import com.marktoledo.todolistapi.exception.UsernameAlreadyExistException;
import com.marktoledo.todolistapi.repository.UserRepository;
import com.marktoledo.todolistapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {

//      validate if password match
        if(!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())){
            throw new PasswordDidNotMatchException("Password did not match");
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

    private void checkIfUsernameExist(String username){
        User user = userRepository.getUserByUsername(username);

        if(user == null){
            throw new UsernameAlreadyExistException("Username already exist");
        }

    }
}
