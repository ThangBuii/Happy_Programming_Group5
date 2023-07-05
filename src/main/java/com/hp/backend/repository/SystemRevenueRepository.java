package com.hp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hp.backend.entity.System_Revenue;

@Repository
public interface SystemRevenueRepository extends JpaRepository<System_Revenue, Integer>{
    
}
