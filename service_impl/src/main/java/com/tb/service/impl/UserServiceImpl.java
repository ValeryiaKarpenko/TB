package com.tb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tb.dal.api.AccountDao;
import com.tb.dal.api.RoleDao;
import com.tb.dal.api.UserDao;
import com.tb.dto.UserDto;
import com.tb.enums.UserRole;
import com.tb.model.Account;
import com.tb.model.AccountBlockchain;
import com.tb.model.User;
import com.tb.service.api.AccountBlockchainService;
import com.tb.service.api.UserService;
import com.tb.service.impl.exceptions.BadDataException;
import com.tb.service.impl.exceptions.DataNotFoundException;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final String NOT_FOUND_MSG = "User with {0} {1} not found";

    @Autowired
    private UserDao userDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AccountBlockchainService accountBlockchainService;

    @Override
    public void createUser(UserDto userDto) {
        if (userDao.getUserByEmail(userDto.getEmail()) != null) {
            throw new BadDataException("Email already taken!");
        }
        if (accountDao.getAccountByLogin(userDto.getLogin()) != null) {
            throw new BadDataException("Login already taken!");
        }
        AccountBlockchain newAccountBlockchain = accountBlockchainService.createAccountBlockchain(0);
        User newUser = new User();
        newUser.merge(userDto);
        newUser.setAccountBlockchain(newAccountBlockchain);
        newUser.setRoles(Arrays.asList(roleDao.findByName(UserRole.USER)));
        userDao.create(newUser);
        
        Account newAccount = new Account();
        newAccount.setLogin(userDto.getLogin());
        newAccount.setPassword(userDto.getPassword());
        newAccount.setUser(newUser);
        accountDao.create(newAccount);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteByID(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public List<User> getAllUsersWithRoles() {
        return userDao.getAllUsersWithRoles();
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userDao.getUserByEmail(email);
        if (user == null) {
            throw new DataNotFoundException(MessageFormat.format(NOT_FOUND_MSG, "email", email));
        }
        return user;
    }

    @Override
    public User getUserById(Long id) {
        User user = userDao.getUserWithRolesById(id);
        if (user == null) {
            throw new DataNotFoundException(MessageFormat.format(NOT_FOUND_MSG, "id", id));
        }

        return user;
    }

    @Override
    public User getUserByFirstName(String name) {
        User user = userDao.getUserByFirstName(name);
        if (user == null) {
            throw new DataNotFoundException(MessageFormat.format(NOT_FOUND_MSG, "name", name));
        }
        return user;
    }

    @Override
    public void updateUser(Long id, UserDto userDto) {
        User record = userDao.toReference(id);
        if (record == null) {
            throw new DataNotFoundException(MessageFormat.format(NOT_FOUND_MSG, "id", id));
        }
        userDao.update(record.merge(userDto));
    }

    @Override
    public void patchUser(Long id, UserDto userDto) {
        User record = userDao.getById(id);
        if (record == null) {
            throw new DataNotFoundException(MessageFormat.format(NOT_FOUND_MSG, "id", id));
        }
        userDao.update(record.patch(userDto));
    }
}
