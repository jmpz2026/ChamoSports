package com.chamo.chamosports.dto.training;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainingUserStatsResponseDTO {
    private Long userId;
    private Long powerShoot;
    private Long speedShoot;
    private Long effectiveShoot;
}
