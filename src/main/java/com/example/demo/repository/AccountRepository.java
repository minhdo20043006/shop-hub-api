package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);

	Optional<Account> findByUsername(String username);

	Optional<Account> findById(Integer id);

	boolean existsByEmailAndIdNot(String email, Integer id);

	boolean existsByUsernameAndIdNot(String username, Integer id);

}
