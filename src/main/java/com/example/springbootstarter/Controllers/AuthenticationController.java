package com.example.springbootstarter.Controllers;

import com.example.springbootstarter.Services.Impl.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    private JwtServiceImpl jwtService;

    @Autowired
    public AuthenticationController(JwtServiceImpl jwtService){
        this.jwtService = jwtService;
    }
}