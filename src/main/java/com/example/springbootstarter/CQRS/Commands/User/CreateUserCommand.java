package com.example.springbootstarter.CQRS.Commands.User;

import com.example.springbootstarter.Models.User;

public record CreateUserCommand(String Name, String Email) {

    public User FromCommand(CreateUserCommand dto){
        return new User(dto.Name, dto.Email);
    }
}