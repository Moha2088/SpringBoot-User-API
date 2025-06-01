package com.example.springbootstarter.Controllers;

import com.example.springbootstarter.Controllers.Exceptions.UserNotFoundException;
import com.example.springbootstarter.DTOS.CreateUserDTO;
import com.example.springbootstarter.Models.User;
import com.example.springbootstarter.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "Creates a user")
    @ApiResponse(responseCode = "201", description = "Returns Created")
    @PostMapping("/")
    public ResponseEntity<User> CreateUser(@RequestBody CreateUserDTO dto){
        User result = userService.AddUser(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Operation(summary = "Gets a user by Id")
    @ApiResponse(responseCode = "200", description = "Returns OK if user exists")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> GetUser(@PathVariable long id) throws UserNotFoundException{
            Optional<User> result = userService.GetUser(id);
            if(result.equals(Optional.empty())){
                throw new UserNotFoundException(id);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "Gets a list of users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns OK if a user exist"),
            @ApiResponse(responseCode = "404", description = "Returns NotFound if the list is empty")
    })
    @GetMapping("/")
    public ResponseEntity<?> GetUsers(){
        List<User> result = userService.GetUsers();
        return !result.isEmpty()
                ? new ResponseEntity<>(result, HttpStatus.OK)
                : new ResponseEntity<>("No users exist!", HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Deletes a user by Id")
    @ApiResponse(responseCode = "204", description = "Returns NoContent")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> DeleteUser(@PathVariable long id){
        userService.DeleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
