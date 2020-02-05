package com.tb.service.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomCryptEncoder {
    
    public String encode(CharSequence rawPassword) {
        return DigestUtils.md5Hex(rawPassword.toString());
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String md5Hex = DigestUtils.md5Hex(rawPassword.toString());
        return md5Hex.equals(encodedPassword);
    }


}
