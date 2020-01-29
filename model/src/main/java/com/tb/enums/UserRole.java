package com.tb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum UserRole {
    ADMIN("admin"), OWNER("owner"), WAITER("waiter"), USER("user");

    @Getter
    @Setter
    private String value;
}
