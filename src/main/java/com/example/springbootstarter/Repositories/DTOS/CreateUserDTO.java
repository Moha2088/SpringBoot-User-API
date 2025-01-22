package com.example.springbootstarter.Repositories.DTOS;

public class CreateUserDTO {
    public String Name;
    public String Email;

    public CreateUserDTO(String name, String email) {
        Name = name;
        Email = email;
    }
}
