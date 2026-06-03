package com.example.demo.service;

import java.util.List;
import com.example.demo.dtos.OrdersDTO;
import com.example.demo.enums.OrderStatus;

public interface OrderService {
	List<OrdersDTO> findByAccountId(Integer accountId);

	Integer createOrderFromCart(Integer accountId, OrdersDTO orderDTO);

	boolean updateOrderStatus(Integer id, String status);

	OrdersDTO findById(Integer id);

	List<OrdersDTO> findBySellerId(Integer sellerId);

	List<OrdersDTO> findBySellerAndStatuses(Integer sellerId, List<OrderStatus> statuses);

	List<OrdersDTO> findAllOrder();

}