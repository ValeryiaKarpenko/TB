package com.tb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tb.dal.api.AccountDao;
import com.tb.dal.api.RoleDao;
import com.tb.dal.api.UserDao;
import com.tb.dto.UserDto;
import com.tb.enums.UserRole;
import com.tb.model.AccountBlockchain;
import com.tb.model.Role;
import com.tb.model.User;
import com.tb.service.api.AccountBlockchainService;
import com.tb.service.api.AccountService;
import com.tb.service.api.UserService;
import com.tb.service.impl.exceptions.BadDataException;
import com.tb.service.impl.exceptions.DataNotFoundException;
import com.tb.service.utils.ImageSaver;

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
    private AccountService accountService;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AccountBlockchainService accountBlockchainService;

    @Autowired
    private ImageSaver imageSaver;

    @Override
    public void createUser(UserDto userDto) {
        if (userDao.getUserByEmail(userDto.getEmail()) != null) {
            throw new BadDataException("Email already taken!");
        }
        if (accountDao.getAccountByLogin(userDto.getLogin()) != null) {
            throw new BadDataException("Login already taken!");
        }
        AccountBlockchain newAccountBlockchain = accountBlockchainService.createAccountBlockchain();
        User newUser = new User();
        newUser.merge(userDto);
        newUser.setAccountBlockchain(newAccountBlockchain);
        newUser.setRoles(Arrays.asList(roleDao.findByName(UserRole.USER)));
        userDao.create(newUser);
        accountService.createAccount(userDto, newUser);
    }

    @Override
    public User getCurrentUser(String login) {
        User user = accountDao.getAccountByLoginWithUser(login).getUser();
        if (user == null) {
            throw new DataNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public void addRole(User user, UserRole role) {
        List<Role> roles = user.getRoles();
        roles.add(roleDao.findByName(role));
        user.setRoles(roles);
        userDao.update(user);
    }

    @Override
    public User getUserById(Long id) {
        User user = userDao.getById(id);
        if (user == null) {
            throw new DataNotFoundException(MessageFormat.format(NOT_FOUND_MSG, "id", id));
        }
        return user;
    }

    @Override
    public void updateUserImage(Long id, MultipartFile file) {
        User user = getUserById(id);
        String photoUrl = imageSaver.updateImage(file, user.getEmail(), user.getPhotoUrl());
        user.setPhotoUrl(photoUrl);
        userDao.update(user);
    }
}
