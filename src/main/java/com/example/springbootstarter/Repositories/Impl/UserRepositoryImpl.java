package com.example.springbootstarter.Repositories.Impl;

import com.example.springbootstarter.Repositories.DTOS.CreateUserDTO;
import com.example.springbootstarter.Repositories.Interfaces.UserRepository;
import com.example.springbootstarter.Repositories.Models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private List<User> _users;


    public UserRepositoryImpl() {
    }


    @Override
    public Task AddUser(CreateUserDTO dto) throws Exception {

        Boolean isEmailTaken = _users
                .stream()
                .anyMatch(u -> u.Email.equals(dto.Email));

        if (isEmailTaken) {
            throw new Exception("This email is taken!");
        }

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(dto, User.class);
        _users.add(user);
        return null;
    }

    @Override
    public List<User> GetUsers() {
        return _users;
    }

    @Override
    public User GetUserByEmail(String email) {
        var user = _users.stream()
                .filter(u -> u.Email.equals(email))
                .findFirst()
                .get();

        return user;
    }

    @Override
    public void DeleteUser(UUID id) {
        var user = _users.stream()
                .filter(u -> u.Id.equals(id))
                .findFirst()
                .get();

        _users.remove(user);
    }

}
