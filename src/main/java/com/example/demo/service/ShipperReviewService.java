package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.SellerReviewDTO;
import com.example.demo.dtos.ShipperReviewDTO;

public interface ShipperReviewService {

	public List<ShipperReviewDTO> findAll();

	public List<ShipperReviewDTO> findByShipperProfileIdForReview(Integer shipperId);
	
	public boolean Create(ShipperReviewDTO shipperReviewDTO);

	public boolean Update(Integer id, ShipperReviewDTO shipperReviewDTO);

	public boolean Delete(int id);
}
