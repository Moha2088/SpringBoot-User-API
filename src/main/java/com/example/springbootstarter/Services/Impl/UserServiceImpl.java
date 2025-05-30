package com.example.springbootstarter.Services.Impl;

import com.example.springbootstarter.Repositories.DTOS.CreateUserDTO;
import com.example.springbootstarter.Repositories.Interfaces.UserRepository;
import com.example.springbootstarter.Repositories.Models.User;
import com.example.springbootstarter.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User AddUser(CreateUserDTO userDto) {
        User mappedUser = userDto.FromDto(userDto);
        userRepository.save()
    }

    @Override
    public User GetUserByEmail(String email) {
        return null;
    }

    @Override
    public User GetUserById(UUID id) {
        return null;
    }

    @Override
    public List<User> GetUsers() {
        return List.of();
    }

    @Override
    public void DeleteUser(UUID id) {

    }
}
