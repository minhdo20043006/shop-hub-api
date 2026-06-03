package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.PromotionDTO;
import com.example.demo.dtos.PromotionUpdateStatusDTO;
import com.example.demo.enums.PromotionStatus;

public interface PromotionService {
	public boolean Create(PromotionDTO promotionDTO);

	public boolean Update(Integer id, PromotionDTO promotionDTO);

	public boolean UpdateStatus(Integer id, PromotionUpdateStatusDTO dto);

	public boolean Delete(Integer id);

	public List<PromotionDTO> findAllForAdmin();

	List<PromotionDTO> findByStatusForAdmin(PromotionStatus statusPromotion);

	public List<PromotionDTO> findAllActive(PromotionStatus statusPromotion);

	public List<PromotionDTO> findByIdProduct(Integer productId);

	public List<PromotionDTO> findByIdCategory(Integer categoryId);

	List<PromotionDTO> findValidPromotionByProduct(Integer productId);
}
