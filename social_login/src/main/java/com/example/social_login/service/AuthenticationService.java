package com.example.social_login.service;

import com.example.social_login.model.AuthenticationRequest;
import com.example.social_login.model.AuthenticationResponse;
import com.example.social_login.model.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse authentication(AuthenticationRequest request);

    AuthenticationResponse register(RegisterRequest request);
}
