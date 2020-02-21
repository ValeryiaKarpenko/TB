package com.tb.service.api;

import org.springframework.web.multipart.MultipartFile;

import com.tb.dto.UserDto;
import com.tb.enums.UserRole;
import com.tb.model.User;


public interface UserService {

    void createUser(UserDto user);
    
    User getCurrentUser(String login);

    void addRole(User user, UserRole role);

    User getUserById(Long id);

    void updateUserImage(Long id, MultipartFile file);
}
