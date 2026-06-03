package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.SellerReviewDTO;

public interface SellerReviewService {

	public List<SellerReviewDTO> findAll();

	public List<SellerReviewDTO> findBySellerProfileIdForReview(Integer sellerId);

	public boolean Create(SellerReviewDTO sellerReviewDTO);

	public boolean Update(SellerReviewDTO sellerReviewDTO);

	public boolean Delete(int id);

}
