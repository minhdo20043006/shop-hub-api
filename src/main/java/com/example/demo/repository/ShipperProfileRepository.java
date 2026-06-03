package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.SellerReview;
import com.example.demo.entities.ShipperProfile;
import com.example.demo.entities.ShipperReview;

@Repository
public interface ShipperProfileRepository extends JpaRepository<ShipperProfile, Integer>{
	
	

}
