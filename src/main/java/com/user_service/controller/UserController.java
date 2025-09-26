package com.user_service.controller;

import com.user_service.dto.UserCredentialDTO;
import com.user_service.dto.UserSignupDTO;
import com.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register-user-service")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserSignupDTO userSignupDTO){
        userService.createNewUser(userSignupDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/internal/validate-user-credential")
    public ResponseEntity<Void> validateCredentials(@Valid @RequestBody UserCredentialDTO userCredentialDTO){
       userService.checkCredentials(userCredentialDTO);
       return ResponseEntity.ok().build();
    }
}
