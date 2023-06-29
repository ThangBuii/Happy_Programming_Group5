package com.hp.backend.repository;

import java.util.List;
import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hp.backend.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    public Optional<Account> findByEmail(String email);

    List<Account> findAllByRole(int role);
}
