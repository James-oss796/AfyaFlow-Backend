package com.AfyaFlow.demo.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring("Bearer ".length());
        try {
            JwtService.JwtPayload payload = jwtService.validateAndExtract(token);
            if (payload.email() == null || payload.role() == null) {
                chain.doFilter(request, response);
                return;
            }

            // Spring expects authorities like ROLE_PATIENT
            String authority = "ROLE_" + payload.role();
            var authorities = List.of(new SimpleGrantedAuthority(authority));

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(payload.email(), null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException ex) {
            // Invalid token -> treat as anonymous and let Security handle access rules.
        }

        chain.doFilter(request, response);
    }
}

