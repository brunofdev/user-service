package com.user_service.service;

import com.user_service.dto.UserSignupDTO;
import com.user_service.entity.User;
import com.user_service.mapper.UserMapper;
import com.user_service.repository.UserRepository;
import com.user_service.validation.UserValidation;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserValidation userValidation;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserValidation userValidation, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userValidation = userValidation;
    }

    public void createNewUser (UserSignupDTO userSignupDTO){
        userValidation.validate(userSignupDTO);
        User user = userMapper.mapUserSignupDTOtoUser(userSignupDTO);
        userRepository.save(user);


    }
}
