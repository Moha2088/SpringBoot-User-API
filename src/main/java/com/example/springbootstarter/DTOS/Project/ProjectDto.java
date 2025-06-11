package com.example.springbootstarter.DTOS.Project;

import com.example.springbootstarter.DTOS.User.UserGetDto;

import java.util.Date;
import java.util.List;

public record ProjectDto(Long Id, String Name, String Description, Date From, Date To, List<UserGetDto> Users) { }