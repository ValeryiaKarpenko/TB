package com.tb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum UserType {
    OWNER("owner"), WAITER("waiter");

    @Getter
    @Setter
    private String value;
}
