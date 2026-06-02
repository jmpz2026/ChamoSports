package com.chamo.chamosports.repository;

import com.chamo.chamosports.entity.TrainingEntity;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepositoryImplementation<TrainingEntity,Long> {
    List<TrainingEntity> findByTeamIdAndDateBetween(Long teamId, LocalDateTime start, LocalDateTime end);
}
