package com.example.springbootstarter.CQRS.Commands.Project;

import java.sql.Date;

public record CreateProjectCommand(String Name, String Description, Date from, Date to) { }