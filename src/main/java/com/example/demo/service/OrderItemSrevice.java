package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.ProductSoldDTO;

public interface OrderItemSrevice {
	
	public List<ProductSoldDTO> getProductsSoldBySeller(Integer sellerId);
	
}
