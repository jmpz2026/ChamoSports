package com.chamo.chamosports.dto.results;

import com.chamo.chamosports.dto.user.UserStatsResponseDTO;

import java.util.List;

public class ResultResponseDTO {
    private Long teamId;
    private String teamName;
    private List<UserStatsResponseDTO> usersStats;
}
