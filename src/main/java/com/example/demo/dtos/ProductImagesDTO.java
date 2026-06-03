package com.example.demo.dtos;

import java.util.Date;

import com.example.demo.entities.Product;

public class ProductImagesDTO {

	private Integer id;
	private Integer productId;
	private String image;
	private boolean isPrimary;
	private Date createdAt;
	private Date updatedAt;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean isPrimary() {
		return isPrimary;
	}
	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
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
	public ProductImagesDTO(Integer id, Integer productId, String image, boolean isPrimary, Date createdAt,
			Date updatedAt) {
		super();
		this.id = id;
		this.productId = productId;
		this.image = image;
		this.isPrimary = isPrimary;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public ProductImagesDTO() {
		super();
	}
	
	
	
	
	
}
