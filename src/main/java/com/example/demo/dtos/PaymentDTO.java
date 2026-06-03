package com.example.demo.dtos;

import java.util.Date;

import com.example.demo.enums.PaymentStatus;

public class PaymentDTO {
	private Integer id;
	private Integer orderId;
	private float amount;
	private PaymentStatus paymentStatus;
	private String paymentMethod;
	private String transactionId;
	private Date paymentDate;
	private Date createdAt;
	private Date updatedAt;

	public PaymentDTO() {
	}

	public PaymentDTO(Integer id, Integer orderId, float amount, PaymentStatus paymentStatus, String paymentMethod,
			String transactionId, Date paymentDate, Date createdAt, Date updatedAt) {
		this.id = id;
		this.orderId = orderId;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
		this.transactionId = transactionId;
		this.paymentDate = paymentDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	// Getters & Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
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
}