package com.example.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.PromotionCategoryDTO;
import com.example.demo.entities.PromotionCategory;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.PromotionCategoryRepository;
import com.example.demo.repository.PromotionRepository;

@Service
@Transactional
public class PromotionCategoryServiceImpl implements PromotionCategoryService {
	@Autowired
	private PromotionCategoryRepository promotionCategoryRepository;

	@Autowired
	private PromotionRepository promotionRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public boolean assignPromotionToCategory(Integer promotionId, Integer categoryId) {
		if (promotionCategoryRepository.existsByPromotion_IdAndCategory_Id(promotionId, categoryId))
			return false;

		PromotionCategory pc = new PromotionCategory();
		pc.setPromotion(promotionRepository.findById(promotionId).orElse(null));
		pc.setCategory(categoryRepository.findById(categoryId).orElse(null));

		promotionCategoryRepository.save(pc);
		return true;
	}

	@Override
	public boolean removePromotionFromCategory(Integer promotionId, Integer categoryId) {
		promotionCategoryRepository.deleteByPromotion_IdAndCategory_Id(promotionId,categoryId);
		return true;
	}

	@Override
	public List<PromotionCategoryDTO> findByPromotion(Integer promotionId) {
		return promotionCategoryRepository.findByPromotion_Id(promotionId).stream()
				.map(c -> modelMapper.map(c, PromotionCategoryDTO.class)).toList();
	}

	@Override
	public List<PromotionCategoryDTO> findByCategory(Integer categoryId) {
		return promotionCategoryRepository.findByCategory_Id(categoryId).stream()
				.map(c -> modelMapper.map(c, PromotionCategoryDTO.class)).toList();
	}

	@Override
	public boolean existsByPromotionAndCategory(Integer promotionId, Integer categoryId) {
		return promotionCategoryRepository.existsByPromotion_IdAndCategory_Id(promotionId, categoryId);
	}
}
