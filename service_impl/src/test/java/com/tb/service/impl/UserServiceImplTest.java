/*package com.tb.service.impl;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tb.dal.api.AccountDao;
import com.tb.dal.api.RoleDao;
import com.tb.dal.api.UserDao;
import com.tb.dto.UserDto;
import com.tb.model.Account;
import com.tb.model.User;
import com.tb.service.api.AccountBlockchainService;
import com.tb.service.api.AccountService;
import com.tb.service.api.UserService;
import com.tb.service.impl.exceptions.BadDataException;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserDao userDao;

    @MockBean
    private AccountDao accountDao;

    @MockBean
    private AccountService accountService;

    @MockBean
    private RoleDao roleDao;

    @MockBean
    private AccountBlockchainService accountBlockchainService;

    @Mock
    private User user;
    
    @Mock
    private Account account;
    
    @Mock
    private UserDto userDto;

    @Before
    public void setUp() {
        when(user.getEmail()).thenReturn(RandomStringUtils.randomAlphabetic(15));
        when(userDto.getEmail()).thenReturn(RandomStringUtils.randomAlphabetic(15));
        when(userDto.getLogin()).thenReturn(RandomStringUtils.randomAlphabetic(15));
    }

    @Test
    public void shouldCreateNewUserIfUserEmailNotExist() {
        when(userDao.getUserByEmail(anyString())).thenReturn(null);
        userService.createUser(userDto);
        verify(accountService).createAccount(any(UserDto.class), any(User.class));
        verify(accountBlockchainService).createAccountBlockchain();
        verify(userDao).create(any(User.class));
    }
    
    @Test
    public void shouldCreateNewUserIfUserLoginNotExist() {
        when(accountDao.getAccountByLogin(anyString())).thenReturn(null);
        userService.createUser(userDto);
        verify(userDao).create(any(User.class));
    }
    
    @Test(expected=BadDataException.class)
    public void shouldNotCreateNewUserIfUserEmailExist() {
        when(userDao.getUserByEmail(anyString())).thenReturn(user);
        userService.createUser(userDto);
        verify(userDao, never()).create(any(User.class));
    }
    
    @Test(expected=BadDataException.class)
    public void shouldNotCreateNewUserIfUserLoginExist() {
        when(accountDao.getAccountByLogin(anyString())).thenReturn(account);
        userService.createUser(userDto);
        verify(userDao, never()).create(any(User.class));
    }

}*/
