package com.hp.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hp.backend.entity.Booking;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findAllByMenteeId(int menteeId);

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.menteeId = :menteeId")
    int countBookingsByMenteeId(int menteeId);

    @Query("SELECT COUNT(b) AS session_count " +
           "FROM Booking b " +
           "JOIN b.time t " +
           "JOIN t.session s " +
           "WHERE s.mentor_id = :mentor_id")
    int getSessionCountByMentorId(@Param("mentor_id") int mentorId);

    @Query("SELECT COALESCE(SUM(s.price), 0) FROM Booking b JOIN b.time t JOIN t.session s WHERE s.mentor_id = :mentor_id")
    double sumPriceByTimeSessionMentorId(@Param("mentor_id") int mentorId);
}
