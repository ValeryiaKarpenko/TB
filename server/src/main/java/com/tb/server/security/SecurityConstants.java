package com.tb.server.security;

public class SecurityConstants {
    
    public static final String SECRET = "BlochchainSecretKe";
    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_IN_URL = "/users/sign-in";
    public static final String SIGN_UP_URL = "/users/sign-up";
    public static final String LOGIN = "login";


}
