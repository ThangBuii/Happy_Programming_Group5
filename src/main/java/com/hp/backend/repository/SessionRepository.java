package com.hp.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hp.backend.entity.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

    // List<Session> findAllByMentorId(int id);

}
