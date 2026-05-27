package com.chamo.chamosports.service;

import com.chamo.chamosports.Exception.CapacityExceededException;
import com.chamo.chamosports.Exception.ResourceExistsException;
import com.chamo.chamosports.Exception.ResourceNotExistsException;
import com.chamo.chamosports.constant.MessageConstant;
import com.chamo.chamosports.dto.ApiResponseDTO;
import com.chamo.chamosports.dto.result.ResultListRequestDTO;
import com.chamo.chamosports.dto.result.ResultListResponseDTO;
import com.chamo.chamosports.dto.user.UserStatsResponseDTO;
import com.chamo.chamosports.entity.TeamEntity;
import com.chamo.chamosports.entity.TrainingEntity;
import com.chamo.chamosports.repository.ResultRepository;
import com.chamo.chamosports.repository.TeamRepository;
import com.chamo.chamosports.repository.TrainingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static sun.jvm.hotspot.runtime.BasicObjectLock.size;

@Service
public class ResultService {

    private ResultRepository resultRepository;
    private TrainingRepository trainingRepository;
    private TeamRepository teamRepository;

    private Long MINIMUM_TRAININGS = 3L;
    private Long MAXIMUM_TRAININGS = 3L;

    public ApiResponseDTO<ResultListResponseDTO> getResultList(ResultListRequestDTO resultListRequestDTO) {
        LocalDateTime start = LocalDateTime.now().minusDays(7);
        LocalDateTime end = LocalDateTime.now();
        Long teamId = resultListRequestDTO.getTeamId();

        TeamEntity teamEntity = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotExistsException(MessageConstant.TEAM_NOT_FOUND));

        List<TrainingEntity> trainingEntityList = trainingRepository.findByTeamIdAndDateBetween(resultListRequestDTO.getTeamId(),start,end);

        long trainingsDoes = (long) trainingEntityList.size();
        if(trainingsDoes < MINIMUM_TRAININGS || trainingsDoes > MAXIMUM_TRAININGS){
            throw new CapacityExceededException(MessageConstant.RESULT_IS_NOT_ENOUGH);
        };

        trainin

        List<UserStatsResponseDTO> userStatsResponseDTOS = new ArrayList<UserStatsResponseDTO>();



        ResultListResponseDTO resultListResponseDTO = new ResultListResponseDTO();
        resultListResponseDTO.setTeamId(resultListRequestDTO.getTeamId());
        resultListResponseDTO.setTeamName(teamEntity.getName());
        resultListResponseDTO.setUsersStats();
    }
}
