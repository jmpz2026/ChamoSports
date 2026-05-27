package com.chamo.chamosports.enums;

import lombok.Getter;

@Getter
public enum UserRol {
    USER(1L),
    ADMIN(2L);

    private final Long id;

    UserRol(Long id) {
        this.id = id;
    }
}
