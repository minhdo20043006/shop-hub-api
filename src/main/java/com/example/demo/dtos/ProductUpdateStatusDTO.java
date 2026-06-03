package com.example.demo.dtos;

import com.example.demo.enums.ProductStatus;

public class ProductUpdateStatusDTO {
	private ProductStatus status;

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public ProductUpdateStatusDTO(ProductStatus status) {
		super();
		this.status = status;
	}

	public ProductUpdateStatusDTO() {
		super();
	}

	

}
