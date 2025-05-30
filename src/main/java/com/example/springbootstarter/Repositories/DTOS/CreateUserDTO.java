package com.example.springbootstarter.Repositories.DTOS;

import com.example.springbootstarter.Repositories.Models.User;

public class CreateUserDTO {
    public String Name;
    public String Email;

    public User FromDto(CreateUserDTO dto){
        return new User(dto.Name, dto.Email);
    }
}
