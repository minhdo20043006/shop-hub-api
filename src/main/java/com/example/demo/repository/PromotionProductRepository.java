package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.PromotionProduct;

@Repository
public interface PromotionProductRepository extends JpaRepository<PromotionProduct, Integer> {

	List<PromotionProduct> findByProduct_Id(Integer productId);
	
	List<PromotionProduct> findByPromotion_Id(Integer promotionId);

	boolean existsByPromotion_IdAndProduct_Id(Integer promotionId, Integer productId);

	void deleteByPromotion_IdAndProduct_Id(Integer promotionId, Integer productId);
}
