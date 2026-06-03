package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.example.demo.dtos.ProductReviewDTO;
import com.example.demo.dtos.SellerProfileDTO;
import com.example.demo.dtos.SellerReviewDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.Product;
import com.example.demo.entities.ProductReview;
import com.example.demo.entities.SellerProfile;
import com.example.demo.entities.SellerReview;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ProductReviewRepository;
import com.example.demo.repository.SellerProfileRepository;
import com.example.demo.repository.SellerReviewRepository;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

	@Autowired
	private ProductReviewRepository productReviewRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountService accountService;

	@Override
	public List<ProductReviewDTO> findAll() {
		List<ProductReview> productReviews = productReviewRepository.findAll();
		return modelMapper.map(productReviews, new TypeToken<List<ProductReviewDTO>>() {
		}.getType());
	}

	@Transactional
	@Override
	public boolean Create(ProductReviewDTO productReviewDTO) {
		try {
			ProductReview productReview = modelMapper.map(productReviewDTO, ProductReview.class);
			productReview.setCreatedAt(new Date());
			Account account = accountRepository.findById(productReviewDTO.getAccountId())
					.orElseThrow(() -> new RuntimeException("Account not found"));
			productReview.setAccount(account);

			Product product = productRepository.findById(productReviewDTO.getProductId())
					.orElseThrow(() -> new RuntimeException("Seller not found"));
			productReview.setProduct(product);

			productReviewRepository.save(productReview);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Update(ProductReviewDTO productReviewDTO) {
		try {
			ProductReview existingReview = productReviewRepository.findById(productReviewDTO.getId()).orElse(null);

			if (existingReview == null) {
				return false;
			}
			existingReview.setCommentReview(productReviewDTO.getCommentReview());
			existingReview.setRating(productReviewDTO.getRating());
			existingReview.setCreatedAt(new Date());

			productReviewRepository.save(existingReview);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Delete(int id) {
		try {
			productReviewRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<ProductReviewDTO> findByProductIdForReview(Integer productId) {
		List<ProductReview> productReviews = productReviewRepository.findByProductId(productId);
		return modelMapper.map(productReviews, new TypeToken<List<ProductReviewDTO>>() {
		}.getType());
	}

}
