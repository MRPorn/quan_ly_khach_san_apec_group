package com.example.social_login.controller;

import com.example.social_login.dto.AuthenticationRequest;
import com.example.social_login.dto.AuthenticationRespose;
import com.example.social_login.dto.RegisterRequest;
import com.example.social_login.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/quan-ly-khach-san")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationRespose> login(
            @RequestBody AuthenticationRequest request
            ){
        return ResponseEntity.ok(service.authentication(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationRespose> register(
            @RequestBody RegisterRequest request
            ){
        return ResponseEntity.ok(service.register(request));
    }
}
