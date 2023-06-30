package com.hp.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hp.backend.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report,Integer>{
    @Query("SELECT r FROM Report r WHERE r.account_id = :account_id")
    List<Report> findByAccountId(@Param("account_id") int account_id);
}
