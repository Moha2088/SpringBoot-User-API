package com.example.springbootstarter.Controllers.Exceptions;

public class ProjectNotFoundException extends Exception {
    public ProjectNotFoundException(long Id){
        super(String.format("Project with id %s was not found!", Id) );
    }
}
