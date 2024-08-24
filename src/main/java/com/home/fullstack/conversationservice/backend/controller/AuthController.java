package com.home.fullstack.conversationservice.backend.controller;

import com.home.fullstack.conversationservice.backend.config.UserAuthProvider;
import com.home.fullstack.conversationservice.backend.dto.CredentialsDto;
import com.home.fullstack.conversationservice.backend.dto.SignUpDto;
import com.home.fullstack.conversationservice.backend.dto.UserDto;
import com.home.fullstack.conversationservice.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
        UserDto user = userService.login(credentialsDto);

        user.setToken(userAuthProvider.createToken(user.getLogin()));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto) {
        UserDto user = userService.register(signUpDto);

        user.setToken(userAuthProvider.createToken(signUpDto.getLogin()));
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }
}
