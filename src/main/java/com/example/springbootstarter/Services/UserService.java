package com.example.springbootstarter.Services;

import com.example.springbootstarter.DTOS.User.CreateUserDTO;
import com.example.springbootstarter.DTOS.User.UpdateUserDto;
import com.example.springbootstarter.DTOS.User.UserGetDto;
import java.util.List;
import java.util.Optional;

public interface UserService {

    UserGetDto AddUser(CreateUserDTO dto);

    Optional<UserGetDto> GetUserById(long id);

    Optional<UserGetDto> GetUserByEmail(String email);

    List<UserGetDto> GetUsers();

    Optional<UserGetDto> UpdateUser(UpdateUserDto dto);

    Optional<Long> DeleteUser(long id);
}