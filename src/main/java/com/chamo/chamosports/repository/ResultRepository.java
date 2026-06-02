package com.chamo.chamosports.repository;

import com.chamo.chamosports.entity.ResultEntity;
import com.chamo.chamosports.entity.TrainingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<ResultEntity, Long> {
    List<ResultEntity> findByTrainingId(Long trainingId);
}
