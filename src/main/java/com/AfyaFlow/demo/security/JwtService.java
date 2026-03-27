package com.AfyaFlow.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;

@Service
public class JwtService {

    public record JwtPayload(String email, String role, Long userId) {}

    private final SecretKey signingKey;
    private final long accessTokenExpirationMs;

    public JwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token-expiration-ms}") long accessTokenExpirationMs
    ) {
        if (secret == null || secret.isBlank() || "PLEASE_SET_JWT_SECRET".equals(secret)) {
            throw new IllegalStateException("Missing/invalid jwt.secret. Set JWT_SECRET environment variable.");
        }
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpirationMs = accessTokenExpirationMs;
    }

    public String generateAccessToken(String email, String role, Long userId) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + accessTokenExpirationMs);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .claim("role", role)
                .claim("userId", userId)
                .signWith(signingKey)
                .compact();
    }

    public JwtPayload validateAndExtract(String token) throws JwtException {
        Jws<Claims> parsed = Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token);

        Claims claims = parsed.getPayload();

        String email = claims.getSubject();
        String role = claims.get("role", String.class);
        Number userIdNumber = claims.get("userId", Number.class);
        Long userId = userIdNumber != null ? userIdNumber.longValue() : null;

        return new JwtPayload(email, role, userId);
    }
}

