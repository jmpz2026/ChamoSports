package com.chamo.chamosports.dto.result;

import com.chamo.chamosports.dto.user.UserStatsResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResultListResponseDTO {
    private Long teamId;
    private String teamName;
    private List<UserStatsResponseDTO> usersStats;
}
