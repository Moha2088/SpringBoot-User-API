package com.example.springbootstarter.DTOS;

public class UserGetDto {
    public Long Id;
    public String Name;
    public String Email;

    public UserGetDto(Long id, String name, String email) {
        this.Id = id;
        this.Name = name;
        this.Email = email;
    }
}
