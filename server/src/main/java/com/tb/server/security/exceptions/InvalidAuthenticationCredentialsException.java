package com.tb.server.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidAuthenticationCredentialsException extends AuthenticationException {

    private static final long serialVersionUID = 7850495438045071605L;

    public InvalidAuthenticationCredentialsException() {
        super("Invalid password.");
    }
}

