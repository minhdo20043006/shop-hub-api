package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.PromotionAccountDTO;
import com.example.demo.dtos.PromotionDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.Promotion;
import com.example.demo.entities.PromotionAccount;
import com.example.demo.enums.PromotionStatus;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.PromotionAccountRepository;
import com.example.demo.repository.PromotionRepository;

@Service
@Transactional
public class PromotionAccountServiceImpl implements PromotionAccountService {

	@Autowired
	private PromotionAccountRepository promotionAccountRepository;

	@Autowired
	private PromotionRepository promotionRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public boolean assignPromotionToAccount(Integer promotionId, Integer accountId) {
		try {

			if (promotionAccountRepository.existsByAccount_IdAndPromotion_Id(accountId, promotionId)) {
				return false;
			}

			Promotion promotion = promotionRepository.findById(promotionId).orElse(null);
			Account account = accountRepository.findById(accountId).orElse(null);

			if (promotion == null || account == null) {
				return false;
			}

			PromotionAccount pa = new PromotionAccount();
			pa.setPromotion(promotion);
			pa.setAccount(account);

			promotionAccountRepository.save(pa);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean removePromotionFromAccount(Integer promotionId, Integer accountId) {
		try {
			promotionAccountRepository.deleteByAccount_IdAndPromotion_Id(promotionId, accountId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<PromotionAccountDTO> findByAccount(Integer accountId) {
		List<PromotionAccount> promotionAccounts = promotionAccountRepository.findByAccount_Id(accountId);
		return modelMapper.map(promotionAccounts, new TypeToken<List<PromotionAccountDTO>>() {
		}.getType());
	}

	// kiem tra promotion co duc Dung ko
	@Override
	public boolean isAccountEligible(Integer promotionId, Integer accountId) {
		if (!promotionAccountRepository.existsByAccount_IdAndPromotion_Id(promotionId, accountId)) {
			return false;
		}

		Promotion promotion = promotionRepository.findById(promotionId).orElse(null);
		if (promotion == null)
			return false;

		if (promotion.getStatusPromotion() != PromotionStatus.ACTIVE) {
			return false;
		}

		Date now = new Date();

		if (promotion.getStartDate() != null && now.before(promotion.getStartDate())) {
			return false;
		}

		if (promotion.getEndDate() != null && now.after(promotion.getEndDate())) {
			return false;
		}

		return true;
	}

	// kiem tra da dc gan chua
	@Override
	public boolean existsByPromotionAndAccount(Integer promotionId, Integer accountId) {
		return promotionAccountRepository.existsByAccount_IdAndPromotion_Id(promotionId, accountId);
	}

	
	// lay promotion hop le cho account ::)))
	@Override
	public List<PromotionDTO> findValidPromotionByAccount(Integer accountId) {
		Date now = new Date();

		return promotionAccountRepository.findByAccount_Id(accountId).stream().map(PromotionAccount::getPromotion)
				.filter(p -> p.getStatusPromotion() == PromotionStatus.ACTIVE)
				.filter(p -> p.getStartDate() == null || !now.before(p.getStartDate()))
				.filter(p -> p.getEndDate() == null || !now.after(p.getEndDate()))
				.map(p -> modelMapper.map(p, PromotionDTO.class)).toList();

	}

}
