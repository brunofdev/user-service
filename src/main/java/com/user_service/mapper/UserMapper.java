package com.user_service.mapper;

import com.user_service.dto.UserSignupDTO;
import com.user_service.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapUserSignupDTOtoUser(UserSignupDTO userSignupDTO) {
        User user = new User();
        user.setUserName(userSignupDTO.getUserName());
        user.setName(userSignupDTO.getName());
        user.setEmail(userSignupDTO.getEmail());
        user.setPassword(userSignupDTO.getPassword());
        return user;
    }
}
