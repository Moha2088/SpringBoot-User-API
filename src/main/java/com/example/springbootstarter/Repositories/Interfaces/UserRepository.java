package com.example.springbootstarter.Repositories.Interfaces;

import com.example.springbootstarter.Repositories.DTOS.CreateUserDTO;
import com.example.springbootstarter.Repositories.Models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.config.Task;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface UserRepository {
    public User AddUser(CreateUserDTO dto) throws Exception;
    public User GetUserByEmail(String email);
    public User GetUserById(UUID id);
    public List<User> GetUsers();
    public void DeleteUser(UUID id);
}
