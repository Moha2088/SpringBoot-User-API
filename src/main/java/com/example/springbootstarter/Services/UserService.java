package com.example.springbootstarter.Services;

import com.example.springbootstarter.DTOS.CreateUserDTO;
import com.example.springbootstarter.Models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User AddUser(CreateUserDTO dto);
    Optional<User> GetUser(long id);
    List<User> GetUsers();
    void DeleteUser(long id);
}
