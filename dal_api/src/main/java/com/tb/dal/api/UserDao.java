package com.tb.dal.api;

import java.util.List;

import com.tb.model.User;

public interface UserDao extends GenericDao<User, Long> {

    User getUserByEmail(String email);

    User getUserWithRolesById(Long id);

    User getUserByFirstName(String name);

    List<User> getAllUsersWithRoles();

    List<User> getAllFullUsers();

}
