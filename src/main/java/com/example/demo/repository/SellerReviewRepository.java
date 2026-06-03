package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.SellerProfile;
import com.example.demo.entities.SellerReview;

@Repository
public interface SellerReviewRepository extends JpaRepository<SellerReview, Integer> {

	@Query("from SellerReview where sellerProfile.id = :sellerId")
	List<SellerReview> findBySellerId(Integer sellerId);

}
