package com.chamo.chamosports.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenDataDTO {
    private String username;
    private Long employeeId;
    private String role;
}