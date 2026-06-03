package com.example.demo.dtos;

import com.example.demo.enums.ApprovedStatus;

public class SellerStatusDTO {
	private boolean exists;
	private ApprovedStatus status;

	public boolean isExists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}

	public ApprovedStatus getStatus() {
		return status;
	}

	public void setStatus(ApprovedStatus status) {
		this.status = status;
	}

	public SellerStatusDTO(boolean exists, ApprovedStatus status) {
		super();
		this.exists = exists;
		this.status = status;
	}

	public SellerStatusDTO() {
		super();
	}

}
