package com.tb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorStatus {

    UNEXPECTED_ERROR(500), UNAUTHORIZED_REQUEST(401), NOT_FOUND(404), BAD_DATA(400), DATA_DUPLICATION(400);

    @Getter
    private Integer value;
}
