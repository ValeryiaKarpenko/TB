package com.tb.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.tb.dto.CredentialsDto;
import com.tb.server.security.exceptions.InvalidAuthenticationCredentialsException;
import com.tb.service.utils.CustomCryptEncoder;

@Component
public class JwtAuntificationPovider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private CustomCryptEncoder customCryptEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CredentialsDto credentials = (CredentialsDto) authentication.getCredentials();
        UserDetails userDetailes = userDetailsService.loadUserByUsername(credentials.getLogin());
        if (!customCryptEncoder.encode(credentials.getPassword()).equals(userDetailes.getPassword())) {
            throw new InvalidAuthenticationCredentialsException();
        }

        return new UsernamePasswordAuthenticationToken(credentials, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}

