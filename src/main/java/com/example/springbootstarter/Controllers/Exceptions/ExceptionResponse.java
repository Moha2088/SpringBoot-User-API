package com.example.springbootstarter.Controllers.Exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class ExceptionResponse {
    public String Title;
    public String Details;
    public String Date;

    public ExceptionResponse(String title, String details, String date) {
        Title = title;
        Details = details;
        Date = date;
    }
}
