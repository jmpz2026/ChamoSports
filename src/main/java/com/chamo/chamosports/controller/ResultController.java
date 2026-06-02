package com.chamo.chamosports.controller;

import com.chamo.chamosports.dto.ApiResponseDTO;
import com.chamo.chamosports.dto.result.ResultListRequestDTO;
import com.chamo.chamosports.dto.result.ResultListResponseDTO;
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

    @GetMapping
    public ResponseEntity<ApiResponseDTO<ResultListResponseDTO>> getResultList(@RequestBody ResultListRequestDTO resultListRequestDTO) {
        ApiResponseDTO<ResultListResponseDTO> apiResponseDTO = resultService.getResultList(resultListRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponseDTO);
    }
}
