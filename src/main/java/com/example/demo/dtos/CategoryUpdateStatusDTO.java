package com.example.demo.dtos;

import com.example.demo.enums.CategoryStatus;

public class CategoryUpdateStatusDTO {
	private CategoryStatus status;

	public CategoryStatus getStatus() {
		return status;
	}

	public void setStatus(CategoryStatus status) {
		this.status = status;
	}

	public CategoryUpdateStatusDTO(CategoryStatus status) {
		super();
		this.status = status;
	}

	public CategoryUpdateStatusDTO() {
		super();
	}
	
}
