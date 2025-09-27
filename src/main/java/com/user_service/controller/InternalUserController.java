package com.user_service.controller;

import com.user_service.dto.UserCredentialDTO;
import com.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/users") // Um prefixo exclusivo para rotas internas
public class InternalUserController {

    private final UserService userService;

    public InternalUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/validate-credential")
    public ResponseEntity<Void> validateCredentials(@Valid @RequestBody UserCredentialDTO userCredentialDTO) {
        boolean isValid = userService.checkCredentials(userCredentialDTO);
        if (isValid) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}