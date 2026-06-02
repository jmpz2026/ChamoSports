package com.chamo.chamosports.service;

import com.chamo.chamosports.Exception.ResourceExistsException;
import com.chamo.chamosports.constant.MessageConstant;
import com.chamo.chamosports.dto.ApiResponseDTO;
import com.chamo.chamosports.dto.team.TeamRegisterRequestDTO;
import com.chamo.chamosports.dto.team.TeamRegisterResponseDTO;
import com.chamo.chamosports.entity.TeamEntity;
import com.chamo.chamosports.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public ApiResponseDTO<TeamRegisterResponseDTO> register(TeamRegisterRequestDTO teamRegisterRequestDTO) {
        if (teamRepository.existsByName(teamRegisterRequestDTO.getName())){
            throw new ResourceExistsException(MessageConstant.TEAM_ALREADY_EXISTS);
        }

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(teamRegisterRequestDTO.getName());
        teamRepository.save(teamEntity);

        TeamRegisterResponseDTO teamRegisterResponseDTO = new TeamRegisterResponseDTO();
        teamRegisterResponseDTO.setName(teamEntity.getName());
        teamRegisterResponseDTO.setId(teamEntity.getId());

        ApiResponseDTO<TeamRegisterResponseDTO> apiResponseDTO = new ApiResponseDTO<>();
        apiResponseDTO.setMessage(MessageConstant.TEAM_REGISTERED);
        apiResponseDTO.setData(teamRegisterResponseDTO);
        apiResponseDTO.setSuccess(true);
        return apiResponseDTO;
    }
}
