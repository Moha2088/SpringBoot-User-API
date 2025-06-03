package com.example.springbootstarter.Controllers.Exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Long id){
        super(String.format("User with id: %s, was not found!", id));
    }

    public UserNotFoundException(String email){
        super(String.format("User with email: %s, was not found!", email));
    }
}