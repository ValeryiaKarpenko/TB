package com.tb.server.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.tb.server.security.exceptions.AuthenticationExceptionHandler;
import com.tb.server.security.exceptions.InvalidAuthenticationTokenException;

import static java.util.Collections.emptyList;

public class JwtAuthorizationFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthorizationFilter() {
        super("/**");
        super.setAuthenticationFailureHandler(new AuthenticationExceptionHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String headerAuth = request.getHeader(SecurityConstants.HEADER_STRING);
        if (headerAuth == null || !headerAuth.startsWith(SecurityConstants.TOKEN_PREFIX)){
            throw new BadCredentialsException("Authorization header is missing");
        }
        return getAuthentication(request);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authentication) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        if (token != null) {
            String user = null;
            try {
                user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes())).build()
                        .verify(token.replace(SecurityConstants.TOKEN_PREFIX, "")).getSubject();
            } catch (JWTVerificationException e) {
            }
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, emptyList());
            }
        }
        throw new InvalidAuthenticationTokenException();
    }
}
