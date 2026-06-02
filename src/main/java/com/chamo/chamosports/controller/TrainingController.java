package com.chamo.chamosports.controller;

import com.chamo.chamosports.dto.ApiResponseDTO;
import com.chamo.chamosports.dto.training.TrainingRegisterRequestDTO;
import com.chamo.chamosports.dto.training.TrainingRegisterResponseDTO;
import com.chamo.chamosports.dto.user.register.UserRegisterRequestDTO;
import com.chamo.chamosports.dto.user.register.UserRegisterResponseDTO;
import com.chamo.chamosports.enums.UserRol;
import com.chamo.chamosports.security.RequiresRole;
import com.chamo.chamosports.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/training")
public class TrainingController {

    private final TrainingService trainingService;

    @RequiresRole({UserRol.USER, UserRol.ADMIN})
    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<TrainingRegisterResponseDTO>> registerTraining(@RequestBody TrainingRegisterRequestDTO trainingRegisterRequestDTO) {
        ApiResponseDTO<TrainingRegisterResponseDTO> apiResponseDTO = trainingService.registerTraining(trainingRegisterRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponseDTO);
    }

}
