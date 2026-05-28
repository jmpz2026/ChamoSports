package com.chamo.chamosports.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserStatsResponseDTO {
    private Long userId;
    private Long powerShoot;
    private Long speedShoot;
    private Long effectiveShoot;
    private Long totalShoot;
}
