package com.example.springbootstarter.Controllers.Exceptions;

import com.example.springbootstarter.CQRS.Queries.User.GetUserByNameQuery;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Long id){
        super(String.format("User with id: %s, was not found!", id));
    }

    public UserNotFoundException(String email){
        super(String.format("User with email: %s, was not found!", email));
    }

    public UserNotFoundException(GetUserByNameQuery query) {
        super(String.format("User with name: %s, was not found!", query.Name()));
    }
}