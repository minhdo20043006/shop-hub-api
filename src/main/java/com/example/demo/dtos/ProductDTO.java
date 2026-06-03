package com.example.demo.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.entities.Cart;
import com.example.demo.entities.Category;
import com.example.demo.entities.Notification;
import com.example.demo.entities.OrderItem;
import com.example.demo.entities.ProductImages;
import com.example.demo.entities.ProductReview;
import com.example.demo.entities.PromotionProduct;
import com.example.demo.entities.SellerProfile;
import com.example.demo.enums.ProductStatus;

public class ProductDTO {
	private Integer id;
	private Integer categoryId;
	private String categoryName;
	private Integer sellerProfileId;
	private String sellerProfileName;
	private Integer sellerAccountId;
	private String nameProduct;
	private String description;
	private float price;
	private int stockQuantity;
	private String photo;
	private float discountPrice;
	private String sku;
	private ProductStatus status;
	private Float weight;
	private Float dimensions;
	private Boolean isFeatured;
	private Boolean isNew;
	private Date createdAt;
	private Date updatedAt;
	private int reviewCount;
	private float avgRating;

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public float getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(float avgRating) {
		this.avgRating = avgRating;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getSellerProfileId() {
		return sellerProfileId;
	}

	public void setSellerProfileId(Integer sellerProfileId) {
		this.sellerProfileId = sellerProfileId;
	}

	public String getSellerProfileName() {
		return sellerProfileName;
	}

	public void setSellerProfileName(String sellerProfileName) {
		this.sellerProfileName = sellerProfileName;
	}

	public Integer getSellerAccountId() {
		return sellerAccountId;
	}

	public void setSellerAccountId(Integer sellerAccountId) {
		this.sellerAccountId = sellerAccountId;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public float getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(float discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Float getDimensions() {
		return dimensions;
	}

	public void setDimensions(Float dimensions) {
		this.dimensions = dimensions;
	}

	public Boolean getIsFeatured() {
		return isFeatured;
	}

	public void setIsFeatured(Boolean isFeatured) {
		this.isFeatured = isFeatured;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
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

	public ProductDTO(Integer id, Integer categoryId, String categoryName, Integer sellerProfileId,
			String sellerProfileName, Integer sellerAccountId,
			String nameProduct, String description, float price, int stockQuantity, String photo, float discountPrice,
			String sku, ProductStatus status, Float weight, Float dimensions, Boolean isFeatured, Boolean isNew,
			Date createdAt, Date updatedAt, int reviewCount, float avgRating) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.sellerProfileId = sellerProfileId;
		this.sellerProfileName = sellerProfileName;
		this.sellerAccountId = sellerAccountId;
		this.nameProduct = nameProduct;
		this.description = description;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.photo = photo;
		this.discountPrice = discountPrice;
		this.sku = sku;
		this.status = status;
		this.weight = weight;
		this.dimensions = dimensions;
		this.isFeatured = isFeatured;
		this.isNew = isNew;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.reviewCount = reviewCount;
		this.avgRating = avgRating;
	}

	public ProductDTO() {
		super();
	}

}
