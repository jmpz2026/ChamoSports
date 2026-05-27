package com.chamo.chamosports.service;

import com.chamo.chamosports.Exception.CapacityExceededException;
import com.chamo.chamosports.constant.MessageConstant;
import com.chamo.chamosports.dto.ApiResponseDTO;
import com.chamo.chamosports.dto.result.ResultListRequestDTO;
import com.chamo.chamosports.dto.result.ResultListResponseDTO;
import com.chamo.chamosports.repository.ResultRepository;
import com.chamo.chamosports.repository.TrainingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ResultService {

    private ResultRepository resultRepository;
    private TrainingRepository trainingRepository;

    private Long MINIMUM_TRAININGS = 3L;
    private Long MAXIMUM_TRAININGS = 3L;

    public ApiResponseDTO<ResultListResponseDTO> getResultList(ResultListRequestDTO resultListRequestDTO) {
        LocalDateTime start = LocalDateTime.now().minusDays(7);
        LocalDateTime end = LocalDateTime.now();

        if((trainingRepository.findByTeamIdAndDateBetween(resultListRequestDTO.getTeamId(),start,end)).size() < MINIMUM_TRAININGS || (trainingRepository.findByTeamIdAndDateBetween(resultListRequestDTO.getTeamId(),start,end)).size() > MAXIMUM_TRAININGS){
            throw new CapacityExceededException(MessageConstant.RESULT_IS_NOT_ENOUGH);
        };


    }
}
