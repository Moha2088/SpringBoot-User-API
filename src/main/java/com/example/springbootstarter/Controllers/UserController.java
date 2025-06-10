package com.example.springbootstarter.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootstarter.CQRS.Commands.User.CreateUserCommand;
import com.example.springbootstarter.CQRS.Commands.User.DeleteUserCommand;
import com.example.springbootstarter.CQRS.Commands.User.UpdateUserCommand;
import com.example.springbootstarter.CQRS.Queries.User.GetUserByEmailQuery;
import com.example.springbootstarter.CQRS.Queries.User.GetUserByIdQuery;
import com.example.springbootstarter.CQRS.Queries.User.GetUserByNameQuery;
import com.example.springbootstarter.Controllers.Exceptions.UserNotFoundException;
import com.example.springbootstarter.DTOS.User.UserGetDto;
import com.example.springbootstarter.Services.Impl.UserServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }   

    
    @Operation(summary = "Creates a user")
    @ApiResponse(responseCode = "201", description = "Returns Created")
    @PostMapping("/")
    public ResponseEntity<UserGetDto> CreateUser(@RequestBody CreateUserCommand command) {
        UserGetDto result = userServiceImpl.AddUser(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(summary = "Gets a user by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns OK if user exists"),
            @ApiResponse(responseCode = "404", description = "Returns NotFound if user doesn't exist")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserGetDto>> GetUserById(@PathVariable long id) throws UserNotFoundException {
        GetUserByIdQuery query = new GetUserByIdQuery(id);
        Optional<UserGetDto> result = userServiceImpl.GetUserById(query);
        if (result.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "Get a user by Name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Returns OK if a user with the name exist"),
        @ApiResponse(responseCode = "404", description = "Returns NotFound if a user with the name doesn't exist")
    })
    @GetMapping("/name/{name}")
    public ResponseEntity<Optional<UserGetDto>> GetUserByName(@PathVariable String name) throws UserNotFoundException{
        GetUserByNameQuery query = new GetUserByNameQuery(name);
        Optional<UserGetDto> result = userServiceImpl.GetUserByName(query);
        if(result.isEmpty()){
            throw new UserNotFoundException(query);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "Gets a user by Email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns OK if a user with the email is exists"),
            @ApiResponse(responseCode = "400", description = "Returns NotFound if a user with the email doesn't exist")
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<UserGetDto>> GetUserByEmail(@PathVariable String email) throws UserNotFoundException {
        GetUserByEmailQuery query = new GetUserByEmailQuery(email);
        Optional<UserGetDto> result = userServiceImpl.GetUserByEmail(query);
        if (result.isEmpty()) {
            throw new UserNotFoundException(email);
        }
        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "Gets a list of users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns OK if a user exists"),
            @ApiResponse(responseCode = "404", description = "Returns NotFound if the list is empty")
    })
    @GetMapping("/")
    public ResponseEntity<?> GetUsers() {
        List<UserGetDto> result = userServiceImpl.GetUsers();
        return !result.isEmpty()
                ? ResponseEntity.ok().body(result)
                : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Updates a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns OK if user was updated"),
            @ApiResponse(responseCode = "404", description = "Returns NotFound if the user doesn't exist")
    })
    @PutMapping("/")
    public ResponseEntity<Optional<UserGetDto>> UpdateUser(@PathVariable Long id, @RequestBody UpdateUserCommand command) throws UserNotFoundException {
        Optional<UserGetDto> result = userServiceImpl.UpdateUser(id, command);
        if (result.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "Deletes a user by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Returns NoContent"),
            @ApiResponse(responseCode = "404", description = "Returns NotFound if the user doesn't exist")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> DeleteUser(@PathVariable long id) throws UserNotFoundException {
        DeleteUserCommand command = new DeleteUserCommand(id);
        Optional<Long> result = userServiceImpl.DeleteUser(command);
        if (result.isPresent()) {
            throw new UserNotFoundException(result.get());
        }
        return ResponseEntity.noContent().build();
    }
}
