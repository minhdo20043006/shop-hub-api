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
import com.example.demo.dtos.ShipperReviewDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.SellerProfile;
import com.example.demo.entities.SellerReview;
import com.example.demo.entities.ShipperProfile;
import com.example.demo.entities.ShipperReview;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.SellerProfileRepository;
import com.example.demo.repository.SellerReviewRepository;
import com.example.demo.repository.ShipperProfileRepository;
import com.example.demo.repository.ShipperReviewRepository;

@Service
public class ShipperReviewServiceImpl implements ShipperReviewService {

	@Autowired
	private ShipperReviewRepository shipperReviewRepository;

	@Autowired
	private ShipperProfileRepository shipperProfileRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountService accountService;

	@Override
	public List<ShipperReviewDTO> findAll() {
		List<ShipperReview> sellerReviews = shipperReviewRepository.findAll();
		return modelMapper.map(sellerReviews, new TypeToken<List<ShipperReviewDTO>>() {
		}.getType());
	}

	@Transactional
	@Override
	public boolean Create(ShipperReviewDTO shipperReviewDTO) {
		try {
			ShipperReview shipperReview = modelMapper.map(shipperReviewDTO, ShipperReview.class);
			shipperReview.setCreatedAt(new Date());

			Account account = accountRepository.findById(shipperReviewDTO.getAccountId())
					.orElseThrow(() -> new RuntimeException("Account not found"));
			shipperReview.setAccount(account);

			ShipperProfile shipperProfile = shipperProfileRepository.findById(shipperReviewDTO.getShipperProfileId())
					.orElseThrow(() -> new RuntimeException("Seller not found"));
			shipperReview.setShipperProfile(shipperProfile);

			shipperReviewRepository.save(shipperReview);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Update(Integer id, ShipperReviewDTO shipperReviewDTO) {
		try {
			ShipperReview existingReview = shipperReviewRepository.findById(id).orElse(null);

			if (existingReview == null) {
				return false;
			}
			existingReview.setCommentReview(shipperReviewDTO.getCommentReview());
			existingReview.setRating(shipperReviewDTO.getRating());
			existingReview.setCreatedAt(new Date());

			shipperReviewRepository.save(existingReview);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Delete(int id) {
		try {
			shipperReviewRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<ShipperReviewDTO> findByShipperProfileIdForReview(Integer shipperId) {
		List<ShipperReview> shipperReviews = shipperReviewRepository.findByShipperId(shipperId);
		return modelMapper.map(shipperReviews, new TypeToken<List<ShipperReviewDTO>>() {
		}.getType());
	}

}
