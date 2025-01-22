package com.example.springbootstarter.Repositories.Interfaces;

import com.example.springbootstarter.Repositories.DTOS.CreateUserDTO;
import com.example.springbootstarter.Repositories.Models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.config.Task;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    public Task AddUser(CreateUserDTO dto) throws Exception;
    public User GetUserByEmail(String email);
    public List<User> GetUsers();
    public void DeleteUser(UUID id);
}
