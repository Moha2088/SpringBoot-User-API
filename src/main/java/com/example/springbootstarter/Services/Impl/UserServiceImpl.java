package com.example.springbootstarter.Services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootstarter.CQRS.Commands.User.CreateUserCommand;
import com.example.springbootstarter.CQRS.Commands.User.DeleteUserCommand;
import com.example.springbootstarter.CQRS.Commands.User.UpdateUserCommand;
import com.example.springbootstarter.CQRS.Queries.User.GetUserByEmailQuery;
import com.example.springbootstarter.CQRS.Queries.User.GetUserByIdQuery;
import com.example.springbootstarter.CQRS.Queries.User.GetUserByNameQuery;
import com.example.springbootstarter.DTOS.User.UserGetDto;
import com.example.springbootstarter.Models.User;
import com.example.springbootstarter.Repositories.UserRepository;
import com.example.springbootstarter.Services.UserService;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserGetDto AddUser(CreateUserCommand command) {
        User mappedUser = command.FromCommand(command);
        userRepository.save(mappedUser);
        return mappedUser.ToDto();
    }

    @Override
    public Optional<UserGetDto> GetUserById(GetUserByIdQuery query) {
        Optional<User> user = userRepository.findById(query.Id());
       return user.map(User::ToDto);
    }

    @Override
    public Optional<UserGetDto> GetUserByName(GetUserByNameQuery query) {
        Optional<User> user = userRepository.findByName(query.Name());
        return user.map(User::ToDto);
    }

    @Override
    public Optional<UserGetDto> GetUserByEmail(GetUserByEmailQuery query) {
        Optional<User> user = userRepository.findByEmail(query.Email());
        return user.isEmpty() ? Optional.empty() : Optional.of(user.get().ToDto());
    }

    @Override
    public List<UserGetDto> GetUsers() {
        ArrayList<UserGetDto> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> {users.add(user.ToDto());});
        return users;
    }

    @Override
    public Optional<UserGetDto> UpdateUser(Long id, UpdateUserCommand command) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            return Optional.empty();
        }
        User userToUpdate = user.get();
        userToUpdate.setName(command.Name());
        userToUpdate.setEmail(command.Email());

        userRepository.save(userToUpdate);
        return Optional.of(userToUpdate.ToDto());
    }

    @Override
    public Optional<Long> DeleteUser(DeleteUserCommand command) {
        Optional<User> user = userRepository.findById(command.Id());
        if(user.isEmpty()){
            return Optional.of(command.Id());
        }
        userRepository.deleteById(command.Id());
        return Optional.empty();
    }
}