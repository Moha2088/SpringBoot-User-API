package com.example.springbootstarter.Services;

import com.example.springbootstarter.Repositories.DTOS.CreateUserDTO;
import com.example.springbootstarter.Repositories.Models.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User AddUser(CreateUserDTO dto);
    User GetUserByEmail(String email);
    User GetUserById(UUID id);
    List<User> GetUsers();
    void DeleteUser(UUID id);
}