package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.PromotionAccount;

@Repository
public interface PromotionAccountRepository extends JpaRepository<PromotionAccount, Integer> {
	boolean existsByAccount_IdAndPromotion_Id(Integer accountId, Integer promotionId);

	List<PromotionAccount> findByAccount_Id(Integer accountId);

	List<PromotionAccount> findByPromotion_Id(Integer promotionId);

	void deleteByAccount_IdAndPromotion_Id(Integer accountId, Integer promotionId);
}
