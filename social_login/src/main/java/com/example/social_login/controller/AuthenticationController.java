package com.example.social_login.controller;

import com.example.social_login.model.AuthenticationRequest;
import com.example.social_login.model.AuthenticationResponse;
import com.example.social_login.model.RegisterRequest;
import com.example.social_login.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/quan-ly-khach-san")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
            ){
        return ResponseEntity.ok(service.authentication(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
            ){
        return ResponseEntity.ok(service.register(request));
    }
}
