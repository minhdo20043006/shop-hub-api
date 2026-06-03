package com.example.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.PromotionConditionDTO;
import com.example.demo.entities.Promotion;
import com.example.demo.entities.PromotionCondition;
import com.example.demo.repository.PromotionConditionRepository;
import com.example.demo.repository.PromotionRepository;

@Service
@Transactional
public class PromotionConditionServiceImpl implements PromotionConditionService {
	@Autowired
	private PromotionConditionRepository promotionConditionRepository;

	@Autowired
	private PromotionRepository promotionRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public boolean addCondition(Integer promotionId, PromotionConditionDTO dto) {
		Promotion promotion = promotionRepository.findById(promotionId).orElse(null);
		if (promotion == null)
			return false;

		PromotionCondition condition = new PromotionCondition();
		condition.setPromotion(promotion);
		condition.setConditionType(dto.getConditionType());
		condition.setConditionValue(dto.getConditionValue());

		promotionConditionRepository.save(condition);
		return true;
	}

	@Override
	public boolean updateCondition(Integer id, PromotionConditionDTO dto) {
		PromotionCondition condition = promotionConditionRepository.findById(id).orElse(null);
		if (condition == null)
			return false;

		condition.setConditionType(dto.getConditionType());
		condition.setConditionValue(dto.getConditionValue());

		promotionConditionRepository.save(condition);
		return true;
	}

	@Override
	public boolean deleteCondition(Integer id) {
		promotionConditionRepository.deleteById(id);
		return true;
	}

	@Override
	public List<PromotionConditionDTO> findByPromotion(Integer promotionId) {
		return promotionConditionRepository.findByPromotion_Id(promotionId).stream()
				.map(c -> modelMapper.map(c, PromotionConditionDTO.class)).toList();
	}
}
