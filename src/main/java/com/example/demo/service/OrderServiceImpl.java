package com.example.demo.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.AccountDTO;
import com.example.demo.dtos.OrdersDTO;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.Cart;
import com.example.demo.entities.Orders;
import com.example.demo.entities.OrderItem;
import com.example.demo.entities.PaymentMethod;
import com.example.demo.entities.Product;
import com.example.demo.entities.ShipperProfile;
import com.example.demo.enums.OrderStatus;
import com.example.demo.enums.PaymentStatus;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PaymentMethodRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ShipperProfileRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ShipperProfileRepository shipperProfileRepository;

	@Autowired
	private PaymentMethodRepository paymentMethodRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<OrdersDTO> findByAccountId(Integer accountId) {
		List<Orders> orders = orderRepository.findByAccountId(accountId);
		return modelMapper.map(orders, new TypeToken<List<OrdersDTO>>() {
		}.getType());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer createOrderFromCart(Integer accountId, OrdersDTO orderDTO) {

		if (accountId == null || orderDTO == null) {
			throw new IllegalArgumentException("Account ID or OrderDTO cannot be null");
		}

		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new IllegalArgumentException("Account not found with id: " + accountId));

		if (orderDTO.getPaymentMethodId() == null) {
			throw new IllegalArgumentException("Payment method ID is required");
		}

		PaymentMethod paymentMethod = paymentMethodRepository.findById(orderDTO.getPaymentMethodId())
				.orElseThrow(() -> new IllegalArgumentException(
						"Payment method not found with id: " + orderDTO.getPaymentMethodId()));

		List<Cart> carts = cartRepository.findByAccountId(accountId);
		if (carts.isEmpty()) {
			throw new IllegalStateException("Cart is empty");
		}

		Orders order = new Orders();
		order.setAccount(account);
		order.setPaymentMethod(paymentMethod);

		order.setStatusOrder(OrderStatus.CREATED);

		order.setOrderItems(new HashSet<>());

		float cartTotal = 0f;

		for (Cart cart : carts) {
			Product product = cart.getProduct();

			if (cart.getQuantityCart() > product.getStockQuantity()) {
				throw new IllegalStateException("Insufficient stock for product: " + product.getNameProduct());
			}

			OrderItem item = new OrderItem();
			item.setOrder(order);
			item.setProduct(product);
			item.setPrice(cart.getPrice());
			item.setQuantity(cart.getQuantityCart());
			item.setDiscount(0);
			item.setTotalOrderItem(cart.getTotalPrice());

			order.getOrderItems().add(item);

			cartTotal += cart.getTotalPrice();

			product.setStockQuantity(product.getStockQuantity() - cart.getQuantityCart());
			productRepository.save(product);
		}

		float shippingFee = orderDTO.getShippingFee() != null ? orderDTO.getShippingFee() : 0f;

		order.setTotalAmount(cartTotal + shippingFee);
		order.setDiscountAmount(0f);
		order.setFinalAmount(Math.round(order.getTotalAmount()));

		order.setOrderCode("ORD-" + System.currentTimeMillis());
		order.setStatusOrder(OrderStatus.CREATED);

		order.setShippingAddress(
				orderDTO.getShippingAddress() != null ? orderDTO.getShippingAddress() : account.getAddress());

		order.setCreatedAt(new Date());
		order.setUpdatedAt(new Date());

		order = orderRepository.save(order);

		cartRepository.deleteAll(carts);

		logger.info("Order created successfully - Order ID: {}", order.getId());

		return order.getId();
	}

	@Override
	@Transactional
	public boolean updateOrderStatus(Integer id, String status) {
		try {
			if (id == null || status == null || status.trim().isEmpty()) {
				throw new IllegalArgumentException("Order ID and status are required");
			}

			Orders order = orderRepository.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id));

			OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
			order.setStatusOrder(orderStatus);
			order.setUpdatedAt(new Date());

			orderRepository.save(order);

			logger.info("Order status updated: ID {} -> {}", id, orderStatus);
			return true;

		} catch (Exception e) {
			logger.error("Error updating order status for ID {}: {}", id, e.getMessage(), e);
			return false;
		}
	}

	@Override
	public OrdersDTO findById(Integer id) {
		Orders orders = orderRepository.findById(id).get();
		return modelMapper.map(orders, OrdersDTO.class);
	}

	@Override
	public List<OrdersDTO> findBySellerId(Integer sellerId) {
		List<Orders> orders = orderRepository.findDistinctByOrderItems_Product_SellerProfile_Id(sellerId);
		return modelMapper.map(orders, new TypeToken<List<OrdersDTO>>() {
		}.getType());
	}

	@Override
	public List<OrdersDTO> findBySellerAndStatuses(Integer sellerId, List<OrderStatus> statuses) {

		List<Orders> orders;

		if (statuses == null || statuses.isEmpty()) {
			orders = orderRepository.findDistinctByOrderItems_Product_SellerProfile_Id(sellerId);
		} else {
			orders = orderRepository.findDistinctByOrderItems_Product_SellerProfile_IdAndStatusOrderIn(sellerId,
					statuses);
		}

		return modelMapper.map(orders, new TypeToken<List<OrdersDTO>>() {
		}.getType());
	}

	@Override
	public List<OrdersDTO> findAllOrder() {
		List<Orders> orders = orderRepository.findAll();
		return modelMapper.map(orders, new TypeToken<List<OrdersDTO>>() {
		}.getType());
	}

}