package com.hp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.hp.backend.entity.Times;

@Repository
public interface TimeRepository extends JpaRepository<Times, Integer> {

}
