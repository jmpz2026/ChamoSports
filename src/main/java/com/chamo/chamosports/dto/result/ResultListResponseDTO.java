package com.chamo.chamosports.dto.result;

import com.chamo.chamosports.dto.user.UserStatsResponseDTO;

import java.util.List;

public class ResultListResponseDTO {
    private Long teamId;
    private String teamName;
    private List<UserStatsResponseDTO> usersStats;
}
