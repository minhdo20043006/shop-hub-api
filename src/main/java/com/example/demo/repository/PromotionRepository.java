package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Promotion;

import com.example.demo.enums.PromotionStatus;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {

	@Query("SELECT p FROM Promotion p WHERE p.statusPromotion = :statusPromotion")
	List<Promotion> findByStatus(@Param("statusPromotion") PromotionStatus statusPromotion);

	@Query("""
			    SELECT p FROM Promotion p
			    JOIN p.promotionProducts pp
			    WHERE pp.product.id = :productId
			""")
	List<Promotion> findByProductId(@Param("productId") Integer productId);

	@Query("""
			    SELECT p FROM Promotion p
			    JOIN p.promotionCategories pc
			    WHERE pc.category.id = :categoryId
			""")
	List<Promotion> findByCategoryId(@Param("categoryId") Integer categoryId);

	@Query("""
			    SELECT p FROM Promotion p
			    JOIN p.promotionProducts pp
			    WHERE pp.product.id = :productId
			      AND p.statusPromotion = com.example.demo.enums.PromotionStatus.ACTIVE
			      AND CURRENT_DATE BETWEEN p.startDate AND p.endDate
			""")
	List<Promotion> findValidPromotionByProduct(@Param("productId") Integer productId);

}
