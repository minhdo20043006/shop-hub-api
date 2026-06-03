package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.PromotionAccount;
import com.example.demo.entities.PromotionCondition;

@Repository
public interface PromotionConditionRepository extends JpaRepository<PromotionCondition, Integer> {

	List<PromotionCondition> findByPromotion_Id(Integer promotionId);
}
