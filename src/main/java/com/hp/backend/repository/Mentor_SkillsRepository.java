package com.hp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hp.backend.entity.Mentor_Skills;

@Repository
public interface Mentor_SkillsRepository extends JpaRepository<Mentor_Skills, Integer> {
    
}
