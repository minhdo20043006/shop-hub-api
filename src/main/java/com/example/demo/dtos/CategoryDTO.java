package com.example.demo.dtos;

import java.util.Date;

import com.example.demo.entities.Category;
import com.example.demo.enums.CategoryStatus;

public class CategoryDTO {
	private Integer id;
	private Integer categoryId;
	private String categoryNameOrigin;
	private String nameCategory;
	private String description;
	private CategoryStatus status;
	private Date createdAt;
	private Date updatedAt;

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

	public String getCategoryNameOrigin() {
		return categoryNameOrigin;
	}

	public void setCategoryNameOrigin(String categoryNameOrigin) {
		this.categoryNameOrigin = categoryNameOrigin;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategoryStatus getStatus() {
		return status;
	}

	public void setStatus(CategoryStatus status) {
		this.status = status;
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

	public CategoryDTO(Integer id, Integer categoryId, String categoryNameOrigin, String nameCategory,
			String description, CategoryStatus status, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.categoryNameOrigin = categoryNameOrigin;
		this.nameCategory = nameCategory;
		this.description = description;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public CategoryDTO() {
		super();
	}

}
