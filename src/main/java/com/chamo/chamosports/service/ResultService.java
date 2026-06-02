package com.chamo.chamosports.service;

import com.chamo.chamosports.Exception.CapacityExceededException;
import com.chamo.chamosports.Exception.ResourceNotExistsException;
import com.chamo.chamosports.constant.MessageConstant;
import com.chamo.chamosports.dto.ApiResponseDTO;
import com.chamo.chamosports.dto.result.ResultListRequestDTO;
import com.chamo.chamosports.dto.result.ResultListResponseDTO;
import com.chamo.chamosports.dto.result.ResultListTrainingRequestDTO;
import com.chamo.chamosports.dto.result.ResultSummaryResponseDTO;
import com.chamo.chamosports.dto.user.UserStatsResponseDTO;
import com.chamo.chamosports.entity.ResultEntity;
import com.chamo.chamosports.entity.TeamEntity;
import com.chamo.chamosports.entity.TrainingEntity;
import com.chamo.chamosports.entity.UserEntity;
import com.chamo.chamosports.repository.ResultRepository;
import com.chamo.chamosports.repository.TeamRepository;
import com.chamo.chamosports.repository.TrainingRepository;
import com.chamo.chamosports.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    private final UserRepository userRepository;
    private ResultRepository resultRepository;
    private TrainingRepository trainingRepository;
    private TeamRepository teamRepository;

    private Long MINIMUM_TRAININGS = 3L;

    public ResultService(ResultRepository resultRepository, TrainingRepository trainingRepository, TeamRepository teamRepository, UserRepository userRepository) {
        this.resultRepository = resultRepository;
        this.trainingRepository = trainingRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    public ApiResponseDTO<ResultSummaryResponseDTO> getResultListByTeamId(ResultListRequestDTO resultListRequestDTO) {
        LocalDateTime start = LocalDateTime.now().minusDays(7);
        LocalDateTime end = LocalDateTime.now();
        Long teamId = resultListRequestDTO.getTeamId();

        TeamEntity teamEntity = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotExistsException(MessageConstant.TEAM_NOT_FOUND));

        List<TrainingEntity> trainingEntityList = trainingRepository.findByTeamIdAndDateBetween(resultListRequestDTO.getTeamId(),start,end);

        long trainingsDoes = (long) trainingEntityList.size();
        if(trainingsDoes < MINIMUM_TRAININGS){
            throw new CapacityExceededException(MessageConstant.RESULT_INCORRECT_SIZE);
        };

        List<ResultListResponseDTO> resultListResponseDTOList = new ArrayList<>();

        trainingEntityList.forEach(trainingEntity -> {
            List<ResultEntity> resultEntityList = resultRepository.findByTrainingId(trainingEntity.getId());
            resultEntityList.forEach(resultEntity -> {
                UserStatsResponseDTO userStatsResponseDTO = new UserStatsResponseDTO();
                userStatsResponseDTO.setUserId(resultEntity.getUser().getId());
                userStatsResponseDTO.setEffectiveShoot(resultEntity.getEffectiveShoot());
                userStatsResponseDTO.setSpeedShoot(resultEntity.getSpeedShoot());
                userStatsResponseDTO.setPowerShoot(resultEntity.getPowerShoot());
                userStatsResponseDTO.setTotalShoot(resultEntity.getTotalShoot());

                List<UserStatsResponseDTO> userStatsResponseDTOList = new ArrayList<>();
                userStatsResponseDTOList.add(userStatsResponseDTO);

                ResultListResponseDTO resultListResponseDTO = new ResultListResponseDTO();
                resultListResponseDTO.setTrainingId(trainingEntity.getId());
                resultListResponseDTO.setUsersStats(userStatsResponseDTOList);
                resultListResponseDTOList.add(resultListResponseDTO);
            });
        });



        ResultSummaryResponseDTO resultSummaryResponseDTO = new ResultSummaryResponseDTO();
        resultSummaryResponseDTO.setTeamId(resultListRequestDTO.getTeamId());
        resultSummaryResponseDTO.setTeamName(teamEntity.getName());
        resultSummaryResponseDTO.setResultList(resultListResponseDTOList);

        ApiResponseDTO<ResultSummaryResponseDTO> apiResponseDTO = new ApiResponseDTO<>();
        apiResponseDTO.setMessage(MessageConstant.RESULT_GET_SUCCESS);
        apiResponseDTO.setData(resultSummaryResponseDTO);
        apiResponseDTO.setSuccess(true);
        return apiResponseDTO;
    }

    public ApiResponseDTO<ResultSummaryResponseDTO> getResultListByTrainingId(ResultListTrainingRequestDTO resultListTrainingRequestDTO) {
        LocalDateTime start = LocalDateTime.now().minusDays(7);
        LocalDateTime end = LocalDateTime.now();

        TrainingEntity trainingEntity = trainingRepository.findById(resultListTrainingRequestDTO.getTrainingId()).orElseThrow(
                () -> new ResourceNotExistsException(MessageConstant.USER_NOT_FOUND)
        );

        List<ResultListResponseDTO> resultListResponseDTOList = new ArrayList<>();

        List<ResultEntity> resultEntityList = resultRepository.findByTrainingId(trainingEntity.getId());
        resultEntityList.forEach(resultEntity -> {
            UserStatsResponseDTO userStatsResponseDTO = new UserStatsResponseDTO();
            userStatsResponseDTO.setUserId(resultEntity.getUser().getId());
            userStatsResponseDTO.setEffectiveShoot(resultEntity.getEffectiveShoot());
            userStatsResponseDTO.setSpeedShoot(resultEntity.getSpeedShoot());
            userStatsResponseDTO.setPowerShoot(resultEntity.getPowerShoot());
            userStatsResponseDTO.setTotalShoot(resultEntity.getTotalShoot());

            List<UserStatsResponseDTO> userStatsResponseDTOList = new ArrayList<>();
            userStatsResponseDTOList.add(userStatsResponseDTO);

            ResultListResponseDTO resultListResponseDTO = new ResultListResponseDTO();
            resultListResponseDTO.setTrainingId(trainingEntity.getId());
            resultListResponseDTO.setUsersStats(userStatsResponseDTOList);
            resultListResponseDTOList.add(resultListResponseDTO);
        });

        ResultSummaryResponseDTO resultSummaryResponseDTO = new ResultSummaryResponseDTO();
        resultSummaryResponseDTO.setTeamId(trainingEntity.getId());
        resultSummaryResponseDTO.setTeamName(trainingEntity.getTeam().getName());
        resultSummaryResponseDTO.setResultList(resultListResponseDTOList);

        ApiResponseDTO<ResultSummaryResponseDTO> apiResponseDTO = new ApiResponseDTO<>();
        apiResponseDTO.setMessage(MessageConstant.RESULT_GET_SUCCESS);
        apiResponseDTO.setData(resultSummaryResponseDTO);
        apiResponseDTO.setSuccess(true);
        return apiResponseDTO;
    }
}
