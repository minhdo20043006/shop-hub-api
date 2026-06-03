package com.example.demo.dtos;

import com.example.demo.enums.ApprovedStatus;

public class SellerApprovedStatusDTO {

	private ApprovedStatus approvedStatus;

	public ApprovedStatus getApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(ApprovedStatus approvedStatus) {
		this.approvedStatus = approvedStatus;
	}

	public SellerApprovedStatusDTO(ApprovedStatus approvedStatus) {
		super();
		this.approvedStatus = approvedStatus;
	}

	public SellerApprovedStatusDTO() {
		super();
	}

}
