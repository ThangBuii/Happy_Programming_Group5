package com.hp.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hp.backend.entity.Account;
import com.hp.backend.entity.Mentor_Skills;

@Repository
public interface Mentor_SkillsRepository extends JpaRepository<Mentor_Skills, Integer> {
    @Query("SELECT a FROM Account a JOIN Mentor_Skills m ON a.account_id = m.account_id WHERE m.skill_id = :skill_id")
    List<Account> findBySkillId(@Param("skill_id") int skill_id);
}
