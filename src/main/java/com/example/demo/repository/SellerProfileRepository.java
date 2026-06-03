package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Product;
import com.example.demo.entities.SellerProfile;
import com.example.demo.enums.ApprovedStatus;
import com.example.demo.enums.ProductStatus;

@Repository
public interface SellerProfileRepository extends JpaRepository<SellerProfile, Integer> {
	boolean existsByAccount_Id(Integer accountId);

	SellerProfile findTopByAccount_IdOrderByIdDesc(Integer accountId);

	List<SellerProfile> findByApprovedStatus(ApprovedStatus approvedStatus);

}
