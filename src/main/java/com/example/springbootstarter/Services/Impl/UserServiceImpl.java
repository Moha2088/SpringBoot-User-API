package com.example.springbootstarter.Services.Impl;

import com.example.springbootstarter.DTOS.CreateUserDTO;
import com.example.springbootstarter.DTOS.UpdateUserDto;
import com.example.springbootstarter.DTOS.UserGetDto;
import com.example.springbootstarter.Repositories.UserRepository;
import com.example.springbootstarter.Models.User;
import com.example.springbootstarter.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserGetDto AddUser(CreateUserDTO dto) {
        User mappedUser = dto.FromDto(dto);
        userRepository.save(mappedUser);
        return mappedUser.ToDto();
    }

    @Override
    public Optional<UserGetDto> GetUserById(long id) {
        Optional<User> user = userRepository.findById(id);
       return user.map(User::ToDto);
    }

    @Override
    public Optional<UserGetDto> GetUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isEmpty() ? Optional.empty() : Optional.of(user.get().ToDto());
    }

    @Override
    public List<UserGetDto> GetUsers() {
        ArrayList<UserGetDto> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> {users.add(user.ToDto());});
        return users;
    }

    @Override
    public Optional<UserGetDto> UpdateUser(UpdateUserDto dto) {
        Optional<User> user = userRepository.findById(dto.Id);

        if(user.isEmpty()){
            return Optional.empty();
        }
        User userToUpdate = user.get();
        userToUpdate.setName(dto.Name);
        userToUpdate.setEmail(dto.Email);

        userRepository.save(userToUpdate);
        return Optional.of(userToUpdate.ToDto());
    }

    @Override
    public Optional<Long> DeleteUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            return Optional.of(id);
        }
        userRepository.deleteById(id);
        return Optional.empty();
    }
}