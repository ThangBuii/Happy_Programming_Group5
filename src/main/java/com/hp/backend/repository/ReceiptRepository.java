package com.hp.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hp.backend.entity.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    @Query("SELECT r FROM Receipt r WHERE r.booking.mentee_id = :menteeId")
    List<Receipt> findReceiptIdsByMenteeId(@Param("menteeId") int menteeId);

    @Query("SELECT r FROM Receipt r JOIN r.booking b JOIN b.time t JOIN t.session s WHERE s.mentor_id = :mentorId")
    List<Receipt> findReceiptsByMentorId(@Param("mentorId") int mentorId);
    @Query("SELECT MAX(receipt_id) FROM Receipt")
    int getLastestID();
}
