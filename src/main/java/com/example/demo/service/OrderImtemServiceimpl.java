package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ProductSoldDTO;
import com.example.demo.repository.OrderItemRepository;

@Service
public class OrderImtemServiceimpl implements OrderItemSrevice {

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public List<ProductSoldDTO> getProductsSoldBySeller(Integer sellerId) {
		return orderItemRepository.findProductsSoldBySeller(sellerId);
	}

}
