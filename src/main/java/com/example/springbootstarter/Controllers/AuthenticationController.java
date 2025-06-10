package com.example.springbootstarter.Controllers;

import com.example.springbootstarter.Services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    private JwtService jwtService;

    @Autowired
    public AuthenticationController(JwtService jwtService){
        this.jwtService = jwtService;
    }
}