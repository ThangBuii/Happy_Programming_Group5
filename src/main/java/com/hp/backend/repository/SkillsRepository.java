package com.hp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hp.backend.entity.Skills;


@Repository
public interface SkillsRepository extends JpaRepository<Skills, Integer> {
    
}
