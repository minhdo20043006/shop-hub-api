package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.ProductReview;
import com.example.demo.entities.SellerProfile;
import com.example.demo.entities.SellerReview;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Integer> {

	@Query("from ProductReview where product.id = :productId")
	List<ProductReview> findByProductId(Integer productId);

}
