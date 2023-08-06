package com.hp.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hp.backend.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.mentee_id = :menteeId")
    int countBookingsByMenteeId(int menteeId);

    @Query("SELECT COUNT(b) AS session_count " +
            "FROM Booking b " +
            "JOIN b.time t " +
            "JOIN t.session s " +
            "WHERE s.mentor_id = :mentor_id")
    int getSessionCountByMentorId(@Param("mentor_id") int mentorId);

    @Query("SELECT COALESCE(SUM(s.price), 0) FROM Booking b JOIN b.time t JOIN t.session s WHERE s.mentor_id = :mentor_id")
    double sumPriceByTimeSessionMentorId(@Param("mentor_id") int mentorId);

    @Query("SELECT b FROM Booking b WHERE b.mentee_id = :mentee_id")
    List<Booking> findAllByMentee_Id(@Param("mentee_id") int mentee_id);

    @Query("SELECT DISTINCT b.mentee_id FROM Booking b JOIN b.time t JOIN t.session s WHERE s.mentor_id = :mentor_id")
    List<Integer> findMenteeIdsByMentorId(@Param("mentor_id") int mentorId);

    @Query("SELECT COUNT(b)>0 FROM Booking b WHERE b.time.time_id = :timeId")
    boolean checkBookedTime(@Param("timeId") int time_id);
    @Query("SELECT MAX(booking_id) FROM Booking")
    int getLatestID();
}
