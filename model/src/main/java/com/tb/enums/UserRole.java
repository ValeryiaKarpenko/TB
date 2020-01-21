package com.tb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum UserRole {
    ADMIN("admin"), ACCOUNT_OPERATOR("account_operator");

    @Getter
    @Setter
    private String value;
}
