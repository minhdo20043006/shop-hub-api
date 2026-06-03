package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.ShipperReview;

@Repository
public interface ShipperReviewRepository extends JpaRepository<ShipperReview, Integer> {
	@Query("from ShipperReview where shipperProfile.id = :shipperId")
	List<ShipperReview> findByShipperId(Integer shipperId);
}
