package com.chamo.chamosports.service;

import com.chamo.chamosports.Exception.CapacityExceededException;
import com.chamo.chamosports.Exception.ResourceExistsException;
import com.chamo.chamosports.Exception.ResourceNotExistsException;
import com.chamo.chamosports.constant.MessageConstant;
import com.chamo.chamosports.dto.ApiResponseDTO;
import com.chamo.chamosports.dto.result.ResultListRequestDTO;
import com.chamo.chamosports.dto.result.ResultListResponseDTO;
import com.chamo.chamosports.dto.training.TrainingRegisterRequestDTO;
import com.chamo.chamosports.dto.training.TrainingRegisterResponseDTO;
import com.chamo.chamosports.dto.user.register.UserRegisterResponseDTO;
import com.chamo.chamosports.entity.ResultEntity;
import com.chamo.chamosports.entity.TeamEntity;
import com.chamo.chamosports.entity.TrainingEntity;
import com.chamo.chamosports.entity.UserEntity;
import com.chamo.chamosports.repository.TeamRepository;
import com.chamo.chamosports.repository.TrainingRepository;
import com.chamo.chamosports.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private TrainingRepository trainingRepository;

    public TrainingService(TrainingRepository trainingRepository, TeamRepository teamRepository, UserRepository userRepository) {
        this.trainingRepository = trainingRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    private Long MINIMUM_MEMBERS = 3L;
    private Long MAXIMUM_MEMBERS = 7L;
    private Float WEIGHT_POWER = 0.2F;
    private Float WEIGHT_SPEED = 0.3F;
    private Float WEIGHT_EFFECTIVE = 0.5F;

    public ApiResponseDTO<TrainingRegisterResponseDTO> register(TrainingRegisterRequestDTO trainingRegisterRequestDTO) {
        TeamEntity teamEntity = teamRepository.findById(trainingRegisterRequestDTO.getTeamId()).orElseThrow(
                () -> new ResourceNotExistsException(MessageConstant.TEAM_NOT_FOUND)
        );

        long membersTraining = (long) trainingRegisterRequestDTO.getUsersStats().size();
        if(membersTraining < MINIMUM_MEMBERS || membersTraining > MAXIMUM_MEMBERS){
            throw new CapacityExceededException(MessageConstant.TRAINING_MEMBERS_INCORRECT_SIZE);
        };

        TrainingEntity trainingEntity = new TrainingEntity();
        List<ResultEntity> resultEntityList = new ArrayList<>();

        trainingRegisterRequestDTO.getUsersStats().stream().forEach(userStat -> {
            ResultEntity resultEntity = new ResultEntity();
            resultEntity.setTraining(trainingEntity);

            UserEntity userEntity = userRepository.findById(userStat.getUserId()).orElseThrow(
                    () -> new ResourceNotExistsException(MessageConstant.USER_NOT_FOUND)
            );

            Long powerShoot = userStat.getPowerShoot();
            Long speedShoot = userStat.getSpeedShoot();
            Long effectiveShoot = userStat.getEffectiveShoot();


            resultEntity.setUser(userEntity);
            resultEntity.setPowerShoot(powerShoot);
            resultEntity.setSpeedShoot(speedShoot);
            resultEntity.setEffectiveShoot(effectiveShoot);

            float totalShoot = (powerShoot*WEIGHT_POWER) + (speedShoot*WEIGHT_SPEED) + (effectiveShoot*WEIGHT_EFFECTIVE);

            resultEntity.setTotalShoot(totalShoot);
            resultEntityList.add(resultEntity);
        });

        trainingEntity.setTeam(teamEntity);
        trainingEntity.setDate(LocalDateTime.now());
        trainingEntity.setResults(resultEntityList);
        trainingRepository.save(trainingEntity);

        TrainingRegisterResponseDTO trainingRegisterResponseDTO = new TrainingRegisterResponseDTO();
        trainingRegisterResponseDTO.setTeamId(teamEntity.getId());
        trainingRegisterResponseDTO.setTeamName(teamEntity.getName());

        ApiResponseDTO<TrainingRegisterResponseDTO> apiResponseDTO = new ApiResponseDTO<>();
        apiResponseDTO.setMessage(MessageConstant.TRAINING_REGISTERED);
        apiResponseDTO.setData(trainingRegisterResponseDTO);
        apiResponseDTO.setSuccess(true);
        return apiResponseDTO;
    }
}
