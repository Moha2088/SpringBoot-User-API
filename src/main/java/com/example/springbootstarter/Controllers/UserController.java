package com.example.springbootstarter.Controllers;

import com.example.springbootstarter.Controllers.Exceptions.UserNotFoundException;
import com.example.springbootstarter.DTOS.CreateUserDTO;
import com.example.springbootstarter.DTOS.UpdateUserDto;
import com.example.springbootstarter.DTOS.UserGetDto;
import com.example.springbootstarter.Services.Impl.UserServiceImpl;
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
    private UserServiceImpl userServiceImpl;


    @Operation(summary = "Creates a user")
    @ApiResponse(responseCode = "201", description = "Returns Created")
    @PostMapping("/")
    public ResponseEntity<UserGetDto> CreateUser(@RequestBody CreateUserDTO dto) {
        UserGetDto result = userServiceImpl.AddUser(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Operation(summary = "Gets a user by Id")
    @ApiResponse(responseCode = "200", description = "Returns OK if user exists")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserGetDto>> GetUserById(@PathVariable long id) throws UserNotFoundException {
        Optional<UserGetDto> result = userServiceImpl.GetUserById(id);
        if (result.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "Gets a user by Email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns OK if a user with the email is exists"),
            @ApiResponse(responseCode = "400", description = "Returns NotFound if a user with the email doesn't exist")
    })
    @GetMapping("/{email}")
    public ResponseEntity<Optional<UserGetDto>> GetUserByEmail(@PathVariable String email) throws UserNotFoundException {
        Optional<UserGetDto> result = userServiceImpl.GetUserByEmail(email);
        if (result.isEmpty()) {
            throw new UserNotFoundException(email);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "Gets a list of users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns OK if a user exist"),
            @ApiResponse(responseCode = "404", description = "Returns NotFound if the list is empty")
    })
    @GetMapping("/")
    public ResponseEntity<?> GetUsers() {
        List<UserGetDto> result = userServiceImpl.GetUsers();
        return !result.isEmpty()
                ? new ResponseEntity<>(result, HttpStatus.OK)
                : new ResponseEntity<>("No users exist!", HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Updates a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns OK if user was updated"),
            @ApiResponse(responseCode = "404", description = "Returns NotFound if the user doesn't exist")
    })
    @PutMapping("/")
    public ResponseEntity<Optional<UserGetDto>> UpdateUser(@RequestBody UpdateUserDto dto) throws UserNotFoundException {
        Optional<UserGetDto> result = userServiceImpl.UpdateUser(dto);
        if (result.isEmpty()) {
            throw new UserNotFoundException(dto.Id);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "Deletes a user by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Returns NoContent"),
            @ApiResponse(responseCode = "404", description = "Returns NotFound if the user doesn't exist")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> DeleteUser(@PathVariable long id) throws UserNotFoundException {
        Optional<Long> result = userServiceImpl.DeleteUser(id);
        if (result.isPresent()) {
            throw new UserNotFoundException(result.get());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
