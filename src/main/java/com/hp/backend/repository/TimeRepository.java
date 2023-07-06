package com.hp.backend.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hp.backend.entity.Times;

@Repository
public interface TimeRepository extends JpaRepository<Times, Integer> {
    @Query("SELECT t FROM Times t WHERE t.session.session_id = :sessionId AND t.start_date = :startDate")
    List<Times> findStartTimeAndEndTime(@Param("sessionId") int sessionId, @Param("startDate") Date startDate);
}
