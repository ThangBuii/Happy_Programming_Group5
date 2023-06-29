package com.hp.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hp.backend.entity.Feedback;



@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {
    @Query("SELECT f FROM Feedback f WHERE f.mentee_id = :mentee_id")
    List<Feedback> findByMenteeId(@Param("mentee_id") int mentee_id);

    @Query("SELECT f FROM Feedback f WHERE f.mentor_id = :mentor_id")
    List<Feedback> findByMentorId(@Param("mentor_id") int mentor_id);
}
