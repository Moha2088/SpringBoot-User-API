package com.example.springbootstarter.DTOS.User;

import java.sql.Date;
import java.util.List;

public record ProjectGetDto(Long Id, String Name, String Description, Date from, Date to, List<UserGetDto> Users) { }