package com.chamo.chamosports.controller;

import com.chamo.chamosports.dto.ApiResponseDTO;
import com.chamo.chamosports.dto.team.TeamRegisterRequestDTO;
import com.chamo.chamosports.dto.team.TeamRegisterResponseDTO;
import com.chamo.chamosports.enums.UserRol;
import com.chamo.chamosports.security.RequiresRole;
import com.chamo.chamosports.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    @RequiresRole({UserRol.USER, UserRol.ADMIN})
    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<TeamRegisterResponseDTO>> registerTeam(@RequestBody TeamRegisterRequestDTO teamRegisterRequestDTO) {
        ApiResponseDTO<TeamRegisterResponseDTO> apiResponseDTO = teamService.registerTeam(teamRegisterRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponseDTO);
    }
}
