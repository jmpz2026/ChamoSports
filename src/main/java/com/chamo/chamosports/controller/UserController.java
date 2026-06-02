package com.chamo.chamosports.controller;

import com.chamo.chamosports.dto.ApiResponseDTO;
import com.chamo.chamosports.dto.user.login.UserLoginRequestDTO;
import com.chamo.chamosports.dto.user.login.UserLoginResponseDTO;
import com.chamo.chamosports.dto.user.register.UserRegisterRequestDTO;
import com.chamo.chamosports.dto.user.register.UserRegisterResponseDTO;
import com.chamo.chamosports.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<UserRegisterResponseDTO>> registerUser(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        ApiResponseDTO<UserRegisterResponseDTO> apiResponseDTO = userService.register(userRegisterRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO<UserLoginResponseDTO>> loginUser(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        ApiResponseDTO<UserLoginResponseDTO> apiResponseDTO = userService.login(userLoginRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponseDTO);
    }
}
