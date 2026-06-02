package com.chamo.chamosports.controller;

import com.chamo.chamosports.dto.ApiResponseDTO;
import com.chamo.chamosports.dto.result.ResultListRequestDTO;
import com.chamo.chamosports.dto.result.ResultListTrainingRequestDTO;
import com.chamo.chamosports.dto.result.ResultSummaryResponseDTO;
import com.chamo.chamosports.enums.UserRol;
import com.chamo.chamosports.security.RequiresRole;
import com.chamo.chamosports.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/result")
public class ResultController {

    private final ResultService resultService;

    @RequiresRole({UserRol.ADMIN})
    @GetMapping("/team")
    public ResponseEntity<ApiResponseDTO<ResultSummaryResponseDTO>> getResultListByTeamId(@RequestBody ResultListRequestDTO resultListRequestDTO) {
        ApiResponseDTO<ResultSummaryResponseDTO> apiResponseDTO = resultService.getResultListByTeamId(resultListRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponseDTO);
    }

    @RequiresRole({UserRol.ADMIN})
    @GetMapping("/training")
    public ResponseEntity<ApiResponseDTO<ResultSummaryResponseDTO>> getResultListByTrainingId(@RequestBody ResultListTrainingRequestDTO resultListTrainingRequestDTO) {
        ApiResponseDTO<ResultSummaryResponseDTO> apiResponseDTO = resultService.getResultListByTrainingId(resultListTrainingRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponseDTO);
    }
}
