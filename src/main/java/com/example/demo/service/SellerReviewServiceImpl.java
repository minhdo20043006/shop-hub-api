package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.example.demo.dtos.SellerProfileDTO;
import com.example.demo.dtos.SellerReviewDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.SellerProfile;
import com.example.demo.entities.SellerReview;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.SellerProfileRepository;
import com.example.demo.repository.SellerReviewRepository;

@Service
public class SellerReviewServiceImpl implements SellerReviewService {

	@Autowired
	private SellerReviewRepository sellerReviewRepository;

	@Autowired
	private SellerProfileRepository sellerProfileRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountService accountService;

	@Override
	public List<SellerReviewDTO> findAll() {
		List<SellerReview> sellerReviews = sellerReviewRepository.findAll();
		return modelMapper.map(sellerReviews, new TypeToken<List<SellerReviewDTO>>() {
		}.getType());
	}

	@Transactional
	@Override
	public boolean Create(SellerReviewDTO sellerReviewDTO) {
		try {
			SellerReview sellerReview = modelMapper.map(sellerReviewDTO, SellerReview.class);
			sellerReview.setCreatedAt(new Date());
			Account account = accountRepository.findById(sellerReviewDTO.getAccountId())
					.orElseThrow(() -> new RuntimeException("Account not found"));
			sellerReview.setAccount(account);
			
			SellerProfile sellerProfile = sellerProfileRepository.findById(sellerReviewDTO.getSellerProfileId())
					.orElseThrow(() -> new RuntimeException("Seller not found"));
			sellerReview.setSellerProfile(sellerProfile);
			
			sellerReviewRepository.save(sellerReview);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Update(SellerReviewDTO sellerReviewDTO) {
		try {
			SellerReview existingReview = sellerReviewRepository.findById(sellerReviewDTO.getId()).orElse(null);

			if (existingReview == null) {
				return false;
			}
			existingReview.setCommentReview(sellerReviewDTO.getCommentReview());
			existingReview.setRating(sellerReviewDTO.getRating());
			existingReview.setCreatedAt(new Date());

			sellerReviewRepository.save(existingReview);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Delete(int id) {
		try {
			sellerReviewRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<SellerReviewDTO> findBySellerProfileIdForReview(Integer sellerId) {
		List<SellerReview> sellerReviews = sellerReviewRepository.findBySellerId(sellerId);
		return modelMapper.map(sellerReviews, new TypeToken<List<SellerReviewDTO>>() {
		}.getType());
	}

	
	
	
}
