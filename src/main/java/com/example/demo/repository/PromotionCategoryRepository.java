package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.PromotionCategory;
import com.example.demo.entities.PromotionProduct;

@Repository
public interface PromotionCategoryRepository extends JpaRepository<PromotionCategory, Integer> {
	List<PromotionCategory> findByPromotion_Id(Integer promotionId);

	List<PromotionCategory> findByCategory_Id(Integer categoryId);

	boolean existsByPromotion_IdAndCategory_Id(Integer promotionId, Integer categoryId);

	void deleteByPromotion_IdAndCategory_Id(Integer promotionId, Integer categoryId);
}
