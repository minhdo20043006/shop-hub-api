package com.example.demo.dtos;

import com.example.demo.entities.Product;
import com.example.demo.entities.Promotion;

public class PromotionProductDTO {
	private Integer id;
	private Integer promotionId;
	private String promotionName;
	private Integer productId;
	private String productName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
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

	public PromotionProductDTO(Integer id, Integer promotionId, String promotionName, Integer productId,
			String productName) {
		super();
		this.id = id;
		this.promotionId = promotionId;
		this.promotionName = promotionName;
		this.productId = productId;
		this.productName = productName;
	}

	public PromotionProductDTO() {
		super();
	}

}
