package com.chamo.chamosports.service;

import com.chamo.chamosports.Exception.CapacityExceededException;
import com.chamo.chamosports.Exception.ResourceNotExistsException;
import com.chamo.chamosports.constant.MessageConstant;
import com.chamo.chamosports.dto.ApiResponseDTO;
import com.chamo.chamosports.dto.result.ResultListRequestDTO;
import com.chamo.chamosports.dto.result.ResultListResponseDTO;
import com.chamo.chamosports.dto.user.UserStatsResponseDTO;
import com.chamo.chamosports.entity.ResultEntity;
import com.chamo.chamosports.entity.TeamEntity;
import com.chamo.chamosports.entity.TrainingEntity;
import com.chamo.chamosports.repository.ResultRepository;
import com.chamo.chamosports.repository.TeamRepository;
import com.chamo.chamosports.repository.TrainingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    private ResultRepository resultRepository;
    private TrainingRepository trainingRepository;
    private TeamRepository teamRepository;

    private Long MINIMUM_TRAININGS = 3L;
    private Long MAXIMUM_TRAININGS = 3L;

    public ResultService(ResultRepository resultRepository, TrainingRepository trainingRepository, TeamRepository teamRepository) {
        this.resultRepository = resultRepository;
        this.trainingRepository = trainingRepository;
        this.teamRepository = teamRepository;
    }

    public ApiResponseDTO<ResultListResponseDTO> getResultList(ResultListRequestDTO resultListRequestDTO) {
        LocalDateTime start = LocalDateTime.now().minusDays(7);
        LocalDateTime end = LocalDateTime.now();
        Long teamId = resultListRequestDTO.getTeamId();

        TeamEntity teamEntity = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotExistsException(MessageConstant.TEAM_NOT_FOUND));

        List<TrainingEntity> trainingEntityList = trainingRepository.findByTeamIdAndDateBetween(resultListRequestDTO.getTeamId(),start,end);

        long trainingsDoes = (long) trainingEntityList.size();
        if(trainingsDoes < MINIMUM_TRAININGS || trainingsDoes > MAXIMUM_TRAININGS){
            throw new CapacityExceededException(MessageConstant.RESULT_INCORRECT_SIZE);
        };

        List<Optional<ResultEntity>> resultEntityList = new ArrayList<>();
        trainingEntityList.forEach(trainingEntity -> {
            resultEntityList.add(resultRepository.findById(trainingEntity.getId()));
        });

        List<UserStatsResponseDTO> userStatsResponseDTOS = new ArrayList<UserStatsResponseDTO>();
        resultEntityList.forEach(resultEntity -> {
            UserStatsResponseDTO userStatsResponseDTO = new UserStatsResponseDTO();
            userStatsResponseDTO.setUserId(resultEntity.get().getUser().getId());
            userStatsResponseDTO.setPowerShoot(resultEntity.get().getPowerShoot());
            userStatsResponseDTO.setEffectiveShoot(resultEntity.get().getEffectiveShoot());
            userStatsResponseDTO.setSpeedShoot(resultEntity.get().getSpeedShoot());
            userStatsResponseDTO.setTotalShoot(resultEntity.get().getTotalShoot());
            userStatsResponseDTOS.add(userStatsResponseDTO);
        });

        ResultListResponseDTO resultListResponseDTO = new ResultListResponseDTO();
        resultListResponseDTO.setTeamId(resultListRequestDTO.getTeamId());
        resultListResponseDTO.setTeamName(teamEntity.getName());
        resultListResponseDTO.setUsersStats(userStatsResponseDTOS);

        ApiResponseDTO<ResultListResponseDTO> apiResponseDTO = new ApiResponseDTO<ResultListResponseDTO>();
        apiResponseDTO.setMessage(MessageConstant.RESULT_GET_SUCCESS);
        apiResponseDTO.setData(resultListResponseDTO);
        apiResponseDTO.setSuccess(true);
        return apiResponseDTO;
    }
}
