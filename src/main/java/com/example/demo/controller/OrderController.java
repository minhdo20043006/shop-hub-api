package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.AccountDTO;
import com.example.demo.dtos.OrdersDTO;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.enums.OrderStatus;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping(value = "/{accountId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<List<OrdersDTO>> getOrders(@PathVariable Integer accountId) {
		try {
			return new ResponseEntity<>(orderService.findByAccountId(accountId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/create/{accountId}", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> createOrder(@PathVariable Integer accountId, @RequestBody OrdersDTO orderDTO) {
		try {
			Integer orderId = orderService.createOrderFromCart(accountId, orderDTO);

			if (orderId != null) {
				return new ResponseEntity<>(orderId, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/update/status/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'SELLER', 'SHIPPER')")
	public ResponseEntity<Void> updateStatus(@PathVariable Integer id, @RequestBody String status) {
		try {
			if (orderService.updateOrderStatus(id, status)) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/find-by-id/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrdersDTO> findById(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<OrdersDTO>(orderService.findById(id), HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<OrdersDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/find-by-seller-status")
	public ResponseEntity<List<OrdersDTO>> getOrdersBySeller(@RequestParam Integer sellerId,
			@RequestParam(required = false) List<OrderStatus> statuses) {

		try {
			return ResponseEntity.ok(orderService.findBySellerAndStatuses(sellerId, statuses));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(value = "find-all", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrdersDTO>> findAllOrders() {
		try {
			return new ResponseEntity<List<OrdersDTO>>(orderService.findAllOrder(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<OrdersDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

}