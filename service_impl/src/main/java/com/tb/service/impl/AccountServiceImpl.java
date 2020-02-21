package com.tb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tb.dal.api.AccountDao;
import com.tb.dto.UserDto;
import com.tb.model.Account;
import com.tb.model.User;
import com.tb.service.api.AccountService;
import com.tb.service.utils.CustomCryptEncoder;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    
    @Autowired
    private AccountDao accountDao;
    
    @Autowired
    private CustomCryptEncoder customCryptEncoder;

    @Override
    public void createAccount(UserDto userDto, User user) {
        Account newAccount = new Account();
        newAccount.setLogin(userDto.getLogin());
        newAccount.setPassword(customCryptEncoder.encode(userDto.getPassword()));
        newAccount.setUser(user);
        accountDao.create(newAccount);
    }


}
