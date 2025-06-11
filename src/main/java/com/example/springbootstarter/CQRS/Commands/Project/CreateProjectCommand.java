package com.example.springbootstarter.CQRS.Commands.Project;

import com.example.springbootstarter.Models.Project;

import java.sql.Date;

public record CreateProjectCommand(String Name, String Description, Date From, Date To) {
    public Project FromCommand() {
        return new Project(Name, Description, From, To);
    }
}