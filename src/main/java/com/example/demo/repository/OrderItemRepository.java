package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dtos.ProductSoldDTO;
import com.example.demo.entities.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

	@Query("""
			    SELECT new com.example.demo.dtos.ProductSoldDTO(
			        p.id,
			        p.nameProduct,
			        p.price,
			        SUM(oi.quantity)
			    )
			    FROM OrderItem oi
			    JOIN oi.product p
			    JOIN p.sellerProfile sp
			    JOIN oi.order o
			    WHERE sp.id = :sellerId
			      AND o.statusOrder IN (
			          com.example.demo.enums.OrderStatus.COMPLETED,
			          com.example.demo.enums.OrderStatus.DELIVERED
			      )
			    GROUP BY p.id, p.nameProduct, p.price
			""")
	List<ProductSoldDTO> findProductsSoldBySeller(@Param("sellerId") Integer sellerId);

}
