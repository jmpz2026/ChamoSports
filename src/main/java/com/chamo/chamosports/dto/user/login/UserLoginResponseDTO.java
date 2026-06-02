package com.chamo.chamosports.dto.user.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginResponseDTO {
    private String name;
    private String password;
    private String rol;
}
