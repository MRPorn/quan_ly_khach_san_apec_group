package com.example.social_login.service.implement;

import com.example.social_login.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
    private static final String SECRET_KEY = "4a41c26873a654c0db2a15d258f82d122fa78e69eecfb3d1b2309f161888c9a0";

    @Override
    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String user = extractUsername(jwt);
        return (user.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
    }

    @Override
    public String generateToken(HashMap<String, Objects> extraClaim, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaim)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);

    }

    public boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    public Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }
}
