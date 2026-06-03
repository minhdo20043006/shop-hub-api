package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.PromotionProductDTO;

public interface PromotionProductService {
	public boolean assignPromotionToProduct(Integer promotionId, Integer productId);

	public boolean removePromotionFromProduct(Integer promotionId, Integer productId);

	public List<PromotionProductDTO> findByPromotion(Integer promotionId);

	public List<PromotionProductDTO> findByProduct(Integer productId);

	public boolean existsByPromotionAndProduct(Integer promotionId, Integer productId);
}
