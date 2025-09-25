package com.user_service.service;

import com.user_service.dto.UserCredentialDTO;
import com.user_service.dto.UserSignupDTO;
import com.user_service.entity.User;
import com.user_service.exceptions.InvalidCredentialsException;
import com.user_service.exceptions.UserNotFoundException;
import com.user_service.mapper.UserMapper;
import com.user_service.repository.UserRepository;
import com.user_service.validation.UserValidation;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserValidation userValidation;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserValidation userValidation, UserMapper userMapper, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userValidation = userValidation;
        this.passwordEncoder = passwordEncoder;
    }

    public void createNewUser (UserSignupDTO userSignupDTO){
        userValidation.validate(userSignupDTO);
        String encodedPassword = passwordEncoder.encode(userSignupDTO.getPassword());
        userSignupDTO.setPassword(encodedPassword);
        User user = userMapper.mapUserSignupDTOtoUser(userSignupDTO);
        userRepository.save(user);
    }
    public void checkCredentials(@Valid UserCredentialDTO userCredentialDTO) {
        User user = userRepository.findByUserName(userCredentialDTO.getUserName()).orElseThrow(() -> new UserNotFoundException("Nome de usuario não encontrado"));
        if(!passwordEncoder.matches(userCredentialDTO.getPassword(), user.getPassword()))
            throw new InvalidCredentialsException("Credenciais inválidas");
    }
}
