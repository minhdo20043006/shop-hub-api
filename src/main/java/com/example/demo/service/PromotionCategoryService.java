package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.PromotionCategoryDTO;

public interface PromotionCategoryService {
	public boolean assignPromotionToCategory(Integer promotionId, Integer categoryId);

	public boolean removePromotionFromCategory(Integer promotionId, Integer categoryId);

	public List<PromotionCategoryDTO> findByPromotion(Integer promotionId);

	public List<PromotionCategoryDTO> findByCategory(Integer categoryId);

	public boolean existsByPromotionAndCategory(Integer promotionId, Integer categoryId);
}
