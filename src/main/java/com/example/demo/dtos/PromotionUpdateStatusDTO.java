package com.example.demo.dtos;

import com.example.demo.enums.PromotionStatus;

public class PromotionUpdateStatusDTO {
	private PromotionStatus statusPromotion;

	public PromotionStatus getStatusPromotion() {
		return statusPromotion;
	}

	public void setStatusPromotion(PromotionStatus statusPromotion) {
		this.statusPromotion = statusPromotion;
	}

	public PromotionUpdateStatusDTO(PromotionStatus statusPromotion) {
		super();
		this.statusPromotion = statusPromotion;
	}

	public PromotionUpdateStatusDTO() {
		super();
	}

}
