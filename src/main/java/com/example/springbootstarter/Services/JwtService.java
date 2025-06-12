package com.example.springbootstarter.Services;

import com.example.springbootstarter.Models.User;
import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;

public interface JwtService {
    String generateToken(User user);

    Claims getAllClaims(String token);

    String extractToken(HttpServletRequest request);

    Boolean validateToken(String token);
}