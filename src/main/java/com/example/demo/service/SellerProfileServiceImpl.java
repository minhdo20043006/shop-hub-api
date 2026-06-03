package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.AccountDTO;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.SellerApprovedStatusDTO;
import com.example.demo.dtos.SellerProfileDTO;
import com.example.demo.dtos.SellerStatusDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.Product;
import com.example.demo.entities.SellerProfile;
import com.example.demo.enums.ApprovedStatus;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.SellerProfileRepository;

@Service
public class SellerProfileServiceImpl implements SellerProfileService {

	@Autowired
	private SellerProfileRepository sellerProfileRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountService accountService;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<SellerProfileDTO> findAll() {
		List<SellerProfile> sellerProfiles = sellerProfileRepository.findAll();
		return modelMapper.map(sellerProfiles, new TypeToken<List<SellerProfileDTO>>() {
		}.getType());
	}

	@Transactional
	@Override
	public boolean Create(SellerProfileDTO sellerProfileDTO) {
		try {
			SellerProfile sellerProfile = modelMapper.map(sellerProfileDTO, SellerProfile.class);
			sellerProfile.setLogo("default.png");
			sellerProfile.setCreatedAt(new Date());
			sellerProfile.setUpdatedAt(new Date());
			sellerProfile.setApprovedStatus(ApprovedStatus.PENDING);
			Account account = accountRepository.findById(sellerProfileDTO.getAccountId())
					.orElseThrow(() -> new RuntimeException("Account not found"));
			sellerProfile.setAccount(account);

			sellerProfileRepository.save(sellerProfile);

			accountService.addRoleToAccount(account.getUsername(), "SELLER");

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean Update(SellerProfileDTO sellerProfileDTO) {
		try {
			SellerProfile existingProfile = sellerProfileRepository.findById(sellerProfileDTO.getId()).orElse(null);

			if (existingProfile == null) {
				return false;
			}

			existingProfile.setStoreName(sellerProfileDTO.getStoreName());
			existingProfile.setTaxCode(sellerProfileDTO.getTaxCode());
			existingProfile.setBusinessLicenseNumber(sellerProfileDTO.getBusinessLicenseNumber());
			existingProfile.setStoreAddress(sellerProfileDTO.getStoreAddress());
			existingProfile.setDescription(sellerProfileDTO.getDescription());
			existingProfile.setLogo(
					sellerProfileDTO.getLogo() != null ? sellerProfileDTO.getLogo() : existingProfile.getLogo());
			existingProfile.setUpdatedAt(new Date());

			sellerProfileRepository.save(existingProfile);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean Delete(int id) {
		try {
			sellerProfileRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean existsByAccountId(Integer accountId) {
		return sellerProfileRepository.existsByAccount_Id(accountId);
	}

	public SellerStatusDTO getSellerStatusByAccountId(Integer accountId) {

		SellerProfile seller = sellerProfileRepository.findTopByAccount_IdOrderByIdDesc(accountId);

		SellerStatusDTO dto = new SellerStatusDTO();

		if (seller == null) {
			dto.setExists(false);
		} else {
			dto.setExists(true);
			dto.setStatus(seller.getApprovedStatus());
		}
		return dto;
	}

	@Override
	public SellerProfileDTO findByAccountId(Integer accountId) {
		SellerProfile seller = sellerProfileRepository.findTopByAccount_IdOrderByIdDesc(accountId);
		return modelMapper.map(seller, SellerProfileDTO.class);
	}

	@Override
	public List<SellerProfileDTO> findByStatus(ApprovedStatus status) {

		List<SellerProfile> sellerProfiles = sellerProfileRepository.findByApprovedStatus(status);

		return modelMapper.map(sellerProfiles, new TypeToken<List<SellerProfileDTO>>() {
		}.getType());
	}

	@Override
	@Transactional
	public boolean updateStatusSeller(Integer id, SellerApprovedStatusDTO statusDto) {
		try {
			SellerProfile seller = sellerProfileRepository.findById(id).orElse(null);
			if (seller == null) {
				return false;
			}

			ApprovedStatus status = statusDto.getApprovedStatus();

			seller.setApprovedStatus(status);
			seller.setUpdatedAt(new Date());

			sellerProfileRepository.save(seller);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public SellerProfileDTO findById(Integer id) {
		SellerProfile sellerProfile = sellerProfileRepository.findById(id).get();
		return modelMapper.map(sellerProfile, SellerProfileDTO.class);
	}

}
