package com.tb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String password;
}
