package com.hp.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hp.backend.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    public Optional<Account> findByEmail(String email);

    List<Account> findAllByRole(int role);

    @Query("SELECT COUNT(a) > 0 FROM Account a WHERE a.username = :username")
    boolean existsByUsername(@Param("username") String username);

    @Query("SELECT COUNT(a) > 0 FROM Account a WHERE a.email = :email")
    boolean existsByEmail(@Param("email") String email);
}
