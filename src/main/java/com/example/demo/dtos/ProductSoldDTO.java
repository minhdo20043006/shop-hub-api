package com.example.demo.dtos;

public class ProductSoldDTO {
	private Integer productId;
	private String productName;
	private float price;
	private long totalSold;

	public ProductSoldDTO(Integer productId, String productName, float price, long totalSold) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.totalSold = totalSold;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public long getTotalSold() {
		return totalSold;
	}

	public void setTotalSold(long totalSold) {
		this.totalSold = totalSold;
	}

	public ProductSoldDTO() {
		super();
	}

	// getter

}
