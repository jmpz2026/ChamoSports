package com.chamo.chamosports.repository;

import com.chamo.chamosports.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public boolean existsByName(String name);
}
