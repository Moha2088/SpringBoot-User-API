package com.example.springbootstarter.Services;

import com.example.springbootstarter.DTOS.CreateUserDTO;
import com.example.springbootstarter.Repositories.UserRepository;
import com.example.springbootstarter.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User AddUser(CreateUserDTO dto) {
        User mappedUser = dto.FromDto(dto);
        userRepository.save(mappedUser);
        return mappedUser;
    }

    public Optional<User> GetUser(long id) {
        return userRepository.findById(id);
    }

    public List<User> GetUsers() {
        ArrayList<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public void DeleteUser(long id) {
        userRepository.deleteById(id);
    }
}