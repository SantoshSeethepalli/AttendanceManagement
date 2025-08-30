package com.santosh.SpringSecurity.Authentication.JwtServices;

import com.santosh.SpringSecurity.Application.model.Role;
import com.santosh.SpringSecurity.Authentication.model.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    public SecretKey getSigningKey() {

        // To convert plain string to bytes (UTF-8) and pass to Keys.hmacShaKeyFor() function
        return Keys.hmacShaKeyFor(
                secretKey.getBytes(java.nio.charset.StandardCharsets.UTF_8)
        );
    }

    public String generateToken(long id, String role) {

        HashMap<String,Object> claims = new HashMap<>();
        claims.put("role", role);

        return buildToken(id, claims);
    }

    private String buildToken(long userId, HashMap<String, Object> claims) {

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(String.valueOf(userId))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .and()
                .signWith(getSigningKey())
                .compact();
    }

    // This method extract the userId in the form of string
    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {

        final int userIdFromToken = Integer.parseInt(extractUserId(token));
        Long userIdFromDetails = ((UserPrincipal) userDetails).getId();

        return (userIdFromToken == userIdFromDetails) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}