package com.chamo.chamosports.service;

import com.chamo.chamosports.Exception.ResourceExistsException;
import com.chamo.chamosports.Exception.ResourceNotExistsException;
import com.chamo.chamosports.dto.ApiResponseDTO;
import com.chamo.chamosports.dto.user.UserRegisterRequestDTO;
import com.chamo.chamosports.dto.user.UserRegisterResponseDTO;
import com.chamo.chamosports.entity.TeamEntity;
import com.chamo.chamosports.constant.MessageConstant;
import com.chamo.chamosports.entity.UserEntity;
import com.chamo.chamosports.enums.UserRol;
import com.chamo.chamosports.repository.TeamRepository;
import com.chamo.chamosports.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private TeamRepository teamRepository;

    public ApiResponseDTO<UserRegisterResponseDTO> register(UserRegisterRequestDTO userRegisterRequestDTO) {
        if (userRepository.existsByName(userRegisterRequestDTO.getName())) {
            throw new ResourceExistsException(MessageConstant.USER_ALREADY_EXISTS);
        }

        TeamEntity teamEntity = teamRepository.findById(userRegisterRequestDTO.getTeamId()).orElseThrow(
                () -> new ResourceNotExistsException(MessageConstant.TEAM_NOT_FOUND)
        );

        UserEntity userEntity = new UserEntity();
        userEntity.setTeam(teamEntity);
        userEntity.setName(userRegisterRequestDTO.getName());
        userEntity.setRolId(userRegisterRequestDTO.getRolId());
        userEntity.setPassword(userRegisterRequestDTO.getPassword());
        userRepository.save(userEntity);

        UserRegisterResponseDTO userRegisterResponseDTO = new UserRegisterResponseDTO();
        userRegisterResponseDTO.setUserId(userEntity.getId());
        userRegisterResponseDTO.setRol(String.valueOf(UserRol.valueOf(String.valueOf(userRegisterRequestDTO.getRolId()))));

        ApiResponseDTO<UserRegisterResponseDTO> apiResponseDTO = new ApiResponseDTO();
        apiResponseDTO.setData(userRegisterResponseDTO);
        apiResponseDTO.setMessage(MessageConstant.USER_REGISTERED);
        apiResponseDTO.setSuccess(true);
        return apiResponseDTO;
    }
}
