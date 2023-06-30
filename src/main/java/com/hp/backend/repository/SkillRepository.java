package com.hp.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hp.backend.entity.Skills;



@Repository
public interface SkillRepository extends JpaRepository<Skills,Integer> {
    @Query("SELECT s FROM Mentor_Skills ms JOIN Skills s ON ms.skill_id = s.skill_id WHERE ms.account_id = :account_id")
    List<Skills> findSkillsByMentorId(@Param("account_id")int mentorId);
}
