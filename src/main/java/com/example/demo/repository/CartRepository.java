package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByAccountId(Integer accountId);  
    Optional<Cart> findByAccountIdAndProductId(Integer accountId, Integer productId);
    void deleteByAccountId(Integer accountId);
}