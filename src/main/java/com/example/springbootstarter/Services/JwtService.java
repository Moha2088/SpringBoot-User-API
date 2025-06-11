package com.example.springbootstarter.Services;

import com.example.springbootstarter.Models.User;
import io.jsonwebtoken.Claims;

public interface JwtService {
    String generateToken(User user);

    Claims getAllClaims(String token);
}