package com.example.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.configuration.SecurityConfiguration;
import com.example.demo.dtos.PromotionDTO;
import com.example.demo.dtos.PromotionUpdateStatusDTO;
import com.example.demo.entities.Promotion;
import com.example.demo.enums.PromotionStatus;
import com.example.demo.repository.PromotionRepository;

@Service
public class PromotionServiceImpl implements PromotionService {

	private final SecurityConfiguration securityConfiguration;

	@Autowired
	private PromotionRepository promotionRepository;

	@Autowired
	private ModelMapper modelMapper;

	PromotionServiceImpl(SecurityConfiguration securityConfiguration) {
		this.securityConfiguration = securityConfiguration;
	}

	@Override
	public boolean Create(PromotionDTO promotionDTO) {
		try {
			Promotion promotion = new Promotion();

			promotion.setNamePromotion(promotionDTO.getNamePromotion());
			promotion.setDescription(promotionDTO.getDescription());
			promotion.setDiscountType(promotionDTO.getDiscountType());
			promotion.setDiscountValue(promotionDTO.getDiscountValue());
			promotion.setStartDate(promotionDTO.getStartDate());
			promotion.setEndDate(promotionDTO.getEndDate());
			promotion.setStatusPromotion(PromotionStatus.DRAFT); // hoặc ACTIVE
			promotion.setMaxDiscount(promotionDTO.getMaxDiscount());
			promotion.setMinOrderValue(promotionDTO.getMinOrderValue());
			promotion.setQuantityPromotion(promotionDTO.getQuantityPromotion());
			promotionRepository.save(promotion);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Update(Integer id, PromotionDTO promotionDTO) {
		try {
			Promotion promotion = promotionRepository.findById(id).orElse(null);
			if (promotion == null)
				return false;

			promotion.setNamePromotion(promotionDTO.getNamePromotion());
			promotion.setDescription(promotionDTO.getDescription());
			promotion.setDiscountType(promotionDTO.getDiscountType());
			promotion.setDiscountValue(promotionDTO.getDiscountValue());
			promotion.setStartDate(promotionDTO.getStartDate());
			promotion.setEndDate(promotionDTO.getEndDate());
			promotion.setMaxDiscount(promotionDTO.getMaxDiscount());
			promotion.setMinOrderValue(promotionDTO.getMinOrderValue());
			promotion.setQuantityPromotion(promotionDTO.getQuantityPromotion());
			promotion.setStatusPromotion(PromotionStatus.DRAFT);
			promotionRepository.save(promotion);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean UpdateStatus(Integer id, PromotionUpdateStatusDTO dto) {
		try {
			Promotion promotion = promotionRepository.findById(id).orElse(null);
			if (promotion == null)
				return false;

			promotion.setStatusPromotion(dto.getStatusPromotion());

			promotionRepository.save(promotion);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Delete(Integer id) {
		try {
			Promotion promotion = promotionRepository.findById(id).orElse(null);
			if (promotion == null)
				return false;

			promotion.setStatusPromotion(PromotionStatus.DISABLED);
			promotionRepository.save(promotion);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<PromotionDTO> findAllForAdmin() {
		List<Promotion> promotions = promotionRepository.findAll();

		return modelMapper.map(promotions, new TypeToken<List<PromotionDTO>>() {
		}.getType());
	}

	@Override
	public List<PromotionDTO> findAllActive(PromotionStatus statusPromotion) {
		List<Promotion> promotions = promotionRepository.findByStatus(statusPromotion);
		return modelMapper.map(promotions, new TypeToken<List<PromotionDTO>>() {
		}.getType());
	}

	@Override
	public List<PromotionDTO> findByIdProduct(Integer productId) {
		List<Promotion> promotions = promotionRepository.findByProductId(productId);

		return modelMapper.map(promotions, new TypeToken<List<PromotionDTO>>() {
		}.getType());
	}

	@Override
	public List<PromotionDTO> findByIdCategory(Integer categoryId) {
		List<Promotion> promotions = promotionRepository.findByCategoryId(categoryId);
		return modelMapper.map(promotions, new TypeToken<List<PromotionDTO>>() {
		}.getType());
	}

	@Override
	public List<PromotionDTO> findByStatusForAdmin(PromotionStatus statusPromotion) {
		List<Promotion> promotions = promotionRepository.findByStatus(statusPromotion);

		return modelMapper.map(promotions, new TypeToken<List<PromotionDTO>>() {
		}.getType());
	}

	@Override
	public List<PromotionDTO> findValidPromotionByProduct(Integer productId) {
		List<Promotion> promotions = promotionRepository.findValidPromotionByProduct(productId);
		return modelMapper.map(promotions, new TypeToken<List<PromotionDTO>>() {
		}.getType());
	}

}
