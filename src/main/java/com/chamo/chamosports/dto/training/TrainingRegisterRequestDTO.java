package com.chamo.chamosports.dto.training;

import com.chamo.chamosports.dto.user.UserStatsResponseDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class TrainingRegisterRequestDTO {
    private Long teamId;
    private List<TrainingUserStatsResponseDTO> usersStats;
}
