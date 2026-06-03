package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.ProductReviewDTO;
import com.example.demo.dtos.SellerReviewDTO;

public interface ProductReviewService {

	public List<ProductReviewDTO> findAll();

	public List<ProductReviewDTO> findByProductIdForReview(Integer productId);
	
	public boolean Create(ProductReviewDTO productReviewDTO);

	public boolean Update(ProductReviewDTO productReviewDTO);

	public boolean Delete(int id);
}
