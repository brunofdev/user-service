package com.user_service.controller;

import com.user_service.dto.UserCredentialDTO;
import com.user_service.dto.UserSignupDTO;
import com.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserSignupDTO userSignupDTO){
        userService.createNewUser(userSignupDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
