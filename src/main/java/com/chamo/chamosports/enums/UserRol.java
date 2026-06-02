package com.chamo.chamosports.enums;

import com.chamo.chamosports.Exception.ResourceNotExistsException;
import com.chamo.chamosports.constant.MessageConstant;
import lombok.Getter;

@Getter
public enum UserRol {
    USER(1L),
    ADMIN(2L);

    private final Long id;

    UserRol(Long id) {
        this.id = id;
    }

    public static String getById(Long id) {
        for (UserRol userRol : UserRol.values()) {
            if (userRol.getId().equals(id)) {
                return userRol.name();
            }
        }
        throw new ResourceNotExistsException(MessageConstant.ROL_NOT_EXISTS);
    }
}
