package com.example.social_login.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;

public interface JwtService {

    String extractUsername(String jwt);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String jwt, UserDetails userDetails);

    String generateToken(HashMap<String, Objects> extraClaim, UserDetails userDetails);

//    Claims extractAllClaims(String token);
//
//    Key getSignKey();
//
//    boolean isTokenExpired(String jwt);
//
//    Date extractExpiration(String jwt);
}
