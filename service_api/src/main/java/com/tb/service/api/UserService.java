package com.tb.service.api;

import java.util.List;

import com.tb.dto.UserDto;
import com.tb.model.User;


public interface UserService {

    List<User> getAllUsers();

    List<User> getAllUsersWithRoles();

    User getUserByEmail(String email);

    User getUserById(Long id);

    User getUserByFirstName(String name);

    void createUser(UserDto user);

    void deleteUser(Long id);

    void updateUser(Long id, UserDto user);

    void patchUser(Long id, UserDto user);
}
