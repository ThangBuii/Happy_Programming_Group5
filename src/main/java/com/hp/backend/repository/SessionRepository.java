package com.hp.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hp.backend.entity.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
    @Query("SELECT b FROM Session b WHERE b.mentor_id = :mentor_id")
    List<Session> findAllByMentorId(@Param("mentor_id") int id);

    @Query("SELECT DISTINCT b.mentor_id FROM Session b WHERE b.skill_id IN :skillIds")
    List<Integer> findAllBySkill_ID(@Param("skillIds") List<Integer> skillIds);

    @Query("SELECT DISTINCT b.mentor_id FROM Session b")
    List<Integer> findAllBySkill_ID();

    @Query("SELECT b FROM Session b WHERE b.mentor_id = :mentor_id and b.status = 1")
    List<Session> findAcceptedByMentorId(int mentor_id);

}