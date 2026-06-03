package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.Orders;
import com.example.demo.enums.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
	List<Orders> findByAccountId(Integer accountId);

	List<Orders> findDistinctByOrderItems_Product_SellerProfile_Id(Integer sellerId);

	List<Orders> findDistinctByOrderItems_Product_SellerProfile_IdAndStatusOrderIn(Integer sellerId,
			List<OrderStatus> statuses);
}