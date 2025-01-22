package com.example.springbootstarter.Controllers;

import com.example.springbootstarter.Repositories.DTOS.CreateUserDTO;
import com.example.springbootstarter.Repositories.Interfaces.UserRepository;
import com.example.springbootstarter.Repositories.Models.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository _userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        _userRepository = userRepository;
    }

    @GetMapping("hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @PostMapping("users")
    public ResponseEntity<Task> AddUser(@RequestBody CreateUserDTO dto) throws Exception {
        try {
            var result = _userRepository.AddUser(dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping()
    public ResponseEntity<List<User>> GetUsers(){
        var results = _userRepository.GetUsers();
        return results != null ? new ResponseEntity<>(results, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> GetUserByEmail(@PathVariable String email){
        var result = _userRepository.GetUserByEmail(email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> DeleteUser(@PathVariable UUID id){
        _userRepository.DeleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
