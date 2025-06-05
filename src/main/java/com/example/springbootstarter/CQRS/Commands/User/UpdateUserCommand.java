package com.example.springbootstarter.CQRS.Commands.User;

public record UpdateUserCommand(String Name, String Email) { };