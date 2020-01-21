package com.tb.model.api;

public interface PatchInstance<T, P> {
    T patch(P dto);
}
