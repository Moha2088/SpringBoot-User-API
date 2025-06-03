package com.example.springbootstarter.Controllers.Exceptions.ExceptionHandlers;

import com.example.springbootstarter.Controllers.Exceptions.ExceptionResponse;
import com.example.springbootstarter.Controllers.Exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        final String Title = "User not found!";
        var format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        var formattedDate = LocalDate.now().format(format);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(Title, e.getMessage(), formattedDate));
    }
}