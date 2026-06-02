package com.chamo.chamosports.dto.user.register;

import lombok.Getter;

@Getter
public class UserRegisterRequestDTO {
    private Long rolId;
    private Long teamId;
    private String name;
    private String password;
}
