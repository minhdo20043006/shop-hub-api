package com.example.demo.dtos;

public class PromotionCategoryDTO {
	private Integer id;
	private Integer promotionId;
	private String promotionName;
	private Integer categoryId;
	private String categoryName;

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

	public PromotionCategoryDTO(Integer id, Integer promotionId, String promotionName, Integer categoryId,
			String categoryName) {
		super();
		this.id = id;
		this.promotionId = promotionId;
		this.promotionName = promotionName;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public PromotionCategoryDTO() {
		super();
	}

}
