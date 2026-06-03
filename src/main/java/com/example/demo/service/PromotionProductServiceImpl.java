package com.example.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.PromotionProductDTO;
import com.example.demo.entities.PromotionProduct;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.PromotionProductRepository;
import com.example.demo.repository.PromotionRepository;

@Service
@Transactional
public class PromotionProductServiceImpl implements PromotionProductService {

	@Autowired
	private PromotionProductRepository promotionProductRepository;

	@Autowired
	private PromotionRepository promotionRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public boolean assignPromotionToProduct(Integer promotionId, Integer productId) {
		if (promotionProductRepository.existsByPromotion_IdAndProduct_Id(promotionId, productId))
			return false;

		PromotionProduct pp = new PromotionProduct();
		pp.setPromotion(promotionRepository.findById(promotionId).orElse(null));
		pp.setProduct(productRepository.findById(productId).orElse(null));

		promotionProductRepository.save(pp);
		return true;
	}

	@Override
	public boolean removePromotionFromProduct(Integer promotionId, Integer productId) {
		promotionProductRepository.deleteByPromotion_IdAndProduct_Id(promotionId, productId);
		return true;
	}

	@Override
	public List<PromotionProductDTO> findByPromotion(Integer promotionId) {
		return promotionProductRepository.findByPromotion_Id(promotionId).stream()
				.map(p -> modelMapper.map(p, PromotionProductDTO.class)).toList();
	}

	@Override
	public List<PromotionProductDTO> findByProduct(Integer productId) {
		return promotionProductRepository.findByProduct_Id(productId).stream()
				.map(p -> modelMapper.map(p, PromotionProductDTO.class)).toList();
	}

	@Override
	public boolean existsByPromotionAndProduct(Integer promotionId, Integer productId) {
		return promotionProductRepository.existsByPromotion_IdAndProduct_Id(promotionId, productId);
	}
}
