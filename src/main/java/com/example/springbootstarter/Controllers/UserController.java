package com.example.springbootstarter.Controllers;

import com.example.springbootstarter.DTOS.CreateUserDTO;
import com.example.springbootstarter.Models.User;
import com.example.springbootstarter.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/")
    public ResponseEntity<User> AddUser(@RequestBody CreateUserDTO dto){
        User result = userService.AddUser(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> GetUser(@PathVariable long id){
        Optional<User> result = userService.GetUser(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> GetUsers(@PathVariable long id){
        List<User> result = userService.GetUsers();
        return !result.isEmpty()
                ? new ResponseEntity<>(result, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> DeleteUser(@PathVariable long id){
        userService.DeleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
