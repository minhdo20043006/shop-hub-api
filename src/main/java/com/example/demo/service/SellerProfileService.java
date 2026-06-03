package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.AccountDTO;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.SellerApprovedStatusDTO;
import com.example.demo.dtos.SellerProfileDTO;
import com.example.demo.dtos.SellerStatusDTO;
import com.example.demo.enums.ApprovedStatus;

public interface SellerProfileService {

	public List<SellerProfileDTO> findAll();

	public boolean Create(SellerProfileDTO sellerProfileDTO);

	public boolean Update(SellerProfileDTO sellerProfileDTO);

	public boolean Delete(int id);

	public boolean existsByAccountId(Integer accountId);

	public SellerStatusDTO getSellerStatusByAccountId(Integer accountId);

	public SellerProfileDTO findByAccountId(Integer accountId);

	public List<SellerProfileDTO> findByStatus(ApprovedStatus status);
	
	boolean updateStatusSeller(Integer id, SellerApprovedStatusDTO statusDto);

	public SellerProfileDTO findById(Integer id);
}
