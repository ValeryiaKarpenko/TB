package com.tb.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tb.dal.api.AccountDao;
import com.tb.model.Account;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    private AccountDao accountDao;

    @Override
    public UserDetails loadUserByUsername(String login) {
        Account account = accountDao.getAccountByLogin(login);
        if(account == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(account.getLogin(), account.getPassword(), emptyList());
    }
    
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
