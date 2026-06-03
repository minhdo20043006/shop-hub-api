package com.example.demo.dtos;

import java.util.Date;

public class CartDTO {

	private Integer id;
	private Integer accountId;
	private Integer productId;
	private String accountName;
	private String productName;
	private String productPhoto;
	private int quantityCart;
	private float price;
	private float totalPrice;
	private int discount;
	private Date createdAt;
	private Date updatedAt;

	public CartDTO() {
	}

	public CartDTO(Integer id, Integer accountId, Integer productId, String accountName, String productName,
			String productPhoto, int quantityCart, float price, int discount, float totalPrice, Date createdAt,
			Date updatedAt) {
		this.id = id;
		this.accountId = accountId;
		this.productId = productId;
		this.accountName = accountName;
		this.productName = productName;
		this.quantityCart = quantityCart;
		this.price = price;
		this.totalPrice = totalPrice;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.productPhoto = productPhoto;
	}

	public String getProductPhoto() {
		return productPhoto;
	}

	public void setProductPhoto(String productPhoto) {
		this.productPhoto = productPhoto;
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

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantityCart() {
		return quantityCart;
	}

	public void setQuantityCart(int quantityCart) {
		this.quantityCart = quantityCart;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
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