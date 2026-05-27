package com.chamo.chamosports.service;

import com.chamo.chamosports.dto.ApiResponseDTO;
import com.chamo.chamosports.dto.result.ResultListResponseDTO;
import com.chamo.chamosports.repository.ResultRepository;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

    private ResultRepository resultRepository;

    public ApiResponseDTO<ResultListResponseDTO> getResultList(Long teamId) {

    }
}
