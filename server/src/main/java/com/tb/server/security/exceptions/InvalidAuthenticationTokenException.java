package com.tb.server.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidAuthenticationTokenException extends AuthenticationException {

    private static final long serialVersionUID = -737474923843172196L;

    public InvalidAuthenticationTokenException() {
        super("Invalid authentication token.");
    }
}
