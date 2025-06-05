package com.example.springbootstarter.Services;

import com.example.springbootstarter.CQRS.Commands.User.CreateUserCommand;
import com.example.springbootstarter.CQRS.Commands.User.DeleteUserCommand;
import com.example.springbootstarter.CQRS.Commands.User.UpdateUserCommand;
import com.example.springbootstarter.CQRS.Queries.User.GetUserByEmailQuery;
import com.example.springbootstarter.CQRS.Queries.User.GetUserByIdQuery;
import com.example.springbootstarter.CQRS.Queries.User.GetUserByNameQuery;
import com.example.springbootstarter.DTOS.User.UserGetDto;
import java.util.List;
import java.util.Optional;

public interface UserService {

    UserGetDto AddUser(CreateUserCommand command);

    Optional<UserGetDto> GetUserById(GetUserByIdQuery query);

    Optional<UserGetDto> GetUserByEmail(GetUserByEmailQuery query);

    Optional<UserGetDto> GetUserByName(GetUserByNameQuery query);

    List<UserGetDto> GetUsers();

    Optional<UserGetDto> UpdateUser(Long id, UpdateUserCommand command);

    Optional<Long> DeleteUser(DeleteUserCommand command);
}