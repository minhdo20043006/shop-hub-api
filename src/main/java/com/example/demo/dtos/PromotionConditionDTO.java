package com.example.demo.dtos;

import com.example.demo.enums.ConditionTypePromotionCondition;

public class PromotionConditionDTO {
	private Integer id;
	private Integer promotionId;
	private ConditionTypePromotionCondition conditionType;
	private String conditionValue;

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

	public ConditionTypePromotionCondition getConditionType() {
		return conditionType;
	}

	public void setConditionType(ConditionTypePromotionCondition conditionType) {
		this.conditionType = conditionType;
	}

	public String getConditionValue() {
		return conditionValue;
	}

	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}

	public PromotionConditionDTO(Integer id, Integer promotionId, ConditionTypePromotionCondition conditionType,
			String conditionValue) {
		super();
		this.id = id;
		this.promotionId = promotionId;
		this.conditionType = conditionType;
		this.conditionValue = conditionValue;
	}

	public PromotionConditionDTO() {
		super();
	}

}
