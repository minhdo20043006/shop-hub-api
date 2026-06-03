package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.PromotionConditionDTO;

public interface PromotionConditionService {
	public boolean addCondition(Integer promotionId, PromotionConditionDTO dto);

	public boolean updateCondition(Integer id, PromotionConditionDTO dto);

	public boolean deleteCondition(Integer id);

	public List<PromotionConditionDTO> findByPromotion(Integer promotionId);
}
