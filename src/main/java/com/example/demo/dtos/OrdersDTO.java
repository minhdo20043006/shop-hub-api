package com.example.demo.dtos;

import java.util.Date;
import java.util.List;

import com.example.demo.enums.OrderStatus;

public class OrdersDTO {
	private Integer id;
	private Integer accountId;
	private String accountFullName;
	private Integer shipperProfileId;
	private String shipperName;
	private Integer paymentMethodId;
	private String paymentMethodName;
	private OrderStatus orderStatus;
	private Float totalAmount;
	private String shippingAddress;
	private Float shippingFee;
	private Date orderDate;
	private Date deliveryDate;
	private Date createdAt;
	private Date updatedAt;
	private List<OrderItemDTO> orderItems;

	public OrdersDTO() {
	}

	public OrdersDTO(Integer id, Integer accountId, String accountFullName, Integer shipperProfileId, String shipperName,
			Integer paymentMethodId, String paymentMethodName, OrderStatus orderStatus, Float totalAmount,
			String shippingAddress, Float shippingFee, Date orderDate, Date deliveryDate, Date createdAt,
			Date updatedAt, List<OrderItemDTO> orderItems) {
		this.id = id;
		this.accountId = accountId;
		this.accountFullName = accountFullName;
		this.shipperProfileId = shipperProfileId;
		this.shipperName = shipperName;
		this.paymentMethodId = paymentMethodId;
		this.paymentMethodName = paymentMethodName;
		this.orderStatus = orderStatus;
		this.totalAmount = totalAmount;
		this.shippingAddress = shippingAddress;
		this.shippingFee = shippingFee;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.orderItems = orderItems;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getAccountFullName() {
		return accountFullName;
	}

	public void setAccountFullName(String accountFullName) {
		this.accountFullName = accountFullName;
	}

	public Integer getShipperProfileId() {
		return shipperProfileId;
	}

	public void setShipperProfileId(Integer shipperProfileId) {
		this.shipperProfileId = shipperProfileId;
	}

	public String getShipperName() {
		return shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	public Integer getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(Integer paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public String getPaymentMethodName() {
		return paymentMethodName;
	}

	public void setPaymentMethodName(String paymentMethodName) {
		this.paymentMethodName = paymentMethodName;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Float getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(Float shippingFee) {
		this.shippingFee = shippingFee;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<OrderItemDTO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDTO> orderItems) {
		this.orderItems = orderItems;
	}
}