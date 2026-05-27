package com.chamo.chamosports.service;

import com.chamo.chamosports.Exception.CapacityExceededException;
import com.chamo.chamosports.Exception.ResourceExistsException;
import com.chamo.chamosports.Exception.ResourceNotExistsException;
import com.chamo.chamosports.dto.ApiResponseDTO;
import com.chamo.chamosports.dto.user.login.UserLoginRequestDTO;
import com.chamo.chamosports.dto.user.login.UserLoginResponseDTO;
import com.chamo.chamosports.dto.user.register.UserRegisterRequestDTO;
import com.chamo.chamosports.dto.user.register.UserRegisterResponseDTO;
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

    private Long MAX_MEMBERS = 7L;

    public ApiResponseDTO<UserRegisterResponseDTO> register(UserRegisterRequestDTO userRegisterRequestDTO) {
        if (userRepository.existsByName(userRegisterRequestDTO.getName())) {
            throw new ResourceExistsException(MessageConstant.USER_ALREADY_EXISTS);
        }

        TeamEntity teamEntity = teamRepository.findById(userRegisterRequestDTO.getTeamId()).orElseThrow(
                () -> new ResourceNotExistsException(MessageConstant.TEAM_NOT_FOUND)
        );

        if (teamRepository.findAllById(userRegisterRequestDTO.getTeamId()) > MAX_MEMBERS){
            throw new CapacityExceededException(MessageConstant.TEAM_IS_FULL);
        };

        UserEntity userEntity = new UserEntity();
        userEntity.setTeam(teamEntity);
        userEntity.setName(userRegisterRequestDTO.getName());
        userEntity.setRolId(userRegisterRequestDTO.getRolId());
        userEntity.setPassword(userRegisterRequestDTO.getPassword());
        userRepository.save(userEntity);

        UserRegisterResponseDTO userRegisterResponseDTO = new UserRegisterResponseDTO();
        userRegisterResponseDTO.setUserId(userEntity.getId());
        userRegisterResponseDTO.setRol(String.valueOf(UserRol.valueOf(String.valueOf(userRegisterRequestDTO.getRolId()))));

        ApiResponseDTO<UserRegisterResponseDTO> apiResponseDTO = new ApiResponseDTO<>();
        apiResponseDTO.setMessage(MessageConstant.USER_REGISTERED);
        apiResponseDTO.setData(userRegisterResponseDTO);
        apiResponseDTO.setSuccess(true);
        return apiResponseDTO;
    }

    public ApiResponseDTO<UserLoginResponseDTO> login(UserLoginRequestDTO userLoginRequestDTO) {
        if (!userRepository.existsByName(userLoginRequestDTO.getName())) {
            throw new ResourceExistsException(MessageConstant.USER_NOT_FOUND);
        }

        UserEntity userEntity = userRepository.findByName(userLoginRequestDTO.getName());

        UserLoginResponseDTO userLoginResponseDTO = new UserLoginResponseDTO();
        userLoginResponseDTO.setName(userEntity.getName());
        userLoginResponseDTO.setPassword(userEntity.getPassword());
        userLoginResponseDTO.setRolId(String.valueOf(UserRol.valueOf(String.valueOf(userEntity.getRolId()))));

        ApiResponseDTO<UserLoginResponseDTO> apiResponseDTO = new ApiResponseDTO<>();
        apiResponseDTO.setMessage(MessageConstant.USER_LOGGED);
        apiResponseDTO.setData(userLoginResponseDTO);
        apiResponseDTO.setSuccess(true);
        return apiResponseDTO;
    }
}
