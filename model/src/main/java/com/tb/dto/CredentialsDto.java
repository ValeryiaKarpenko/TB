package com.tb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class CredentialsDto {
    
    @Getter
    @Setter
    private String login;
    @Getter
    @Setter
    private String password;


}
