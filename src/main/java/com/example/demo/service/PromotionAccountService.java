package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.PromotionAccountDTO;
import com.example.demo.dtos.PromotionDTO;

public interface PromotionAccountService {

	public boolean assignPromotionToAccount(Integer promotionId, Integer accountId);

	public boolean removePromotionFromAccount(Integer promotionId, Integer accountId);

	public List<PromotionAccountDTO> findByAccount(Integer accountId);

	public boolean isAccountEligible(Integer promotionId, Integer accountId);
	
	boolean existsByPromotionAndAccount(Integer promotionId, Integer accountId);


	List<PromotionDTO> findValidPromotionByAccount(Integer accountId);
}
