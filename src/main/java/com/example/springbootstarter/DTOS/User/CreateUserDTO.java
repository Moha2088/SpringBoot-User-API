package com.example.springbootstarter.DTOS.User;

import com.example.springbootstarter.Models.User;

public class CreateUserDTO {
    public String Name;
    public String Email;

    public User FromDto(CreateUserDTO dto){
        return new User(dto.Name, dto.Email);
    }
}