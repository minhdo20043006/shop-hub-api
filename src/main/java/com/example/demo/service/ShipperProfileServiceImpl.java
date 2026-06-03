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
import com.example.demo.dtos.SellerProfileDTO;
import com.example.demo.dtos.ShipperProfileDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.SellerProfile;
import com.example.demo.entities.ShipperProfile;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.SellerProfileRepository;
import com.example.demo.repository.ShipperProfileRepository;

@Service
public class ShipperProfileServiceImpl implements ShipperProfileService {

	@Autowired
	private ShipperProfileRepository shipperProfileRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Autowired
	private AccountRepository accountRepository;

	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ShipperProfileDTO> findAll() {
		List<ShipperProfile> shipperProfiles = shipperProfileRepository.findAll();
		return modelMapper.map(shipperProfiles, new TypeToken<List<ShipperProfileDTO>>() {
		}.getType());
	}

	@Transactional
	@Override
	public boolean Create(ShipperProfileDTO shipperProfileDTO) {
	    try {
	        ShipperProfile shipperProfile = modelMapper.map(shipperProfileDTO, ShipperProfile.class);
	        shipperProfile.setCreatedAt(new Date());
	        shipperProfile.setUpdatedAt(new Date());

	        Account account = accountRepository.findById(shipperProfileDTO.getAccountId())
	                .orElseThrow(() -> new RuntimeException("Account not found"));
	        shipperProfile.setAccount(account);

	        shipperProfileRepository.save(shipperProfile);

	        accountService.addRoleToAccount(account.getUsername(), "SHIPPER");

	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	@Transactional
	public boolean Update(Integer id, ShipperProfileDTO shipperProfileDTO) {
	    try {
	        ShipperProfile existingProfile = shipperProfileRepository
	                .findById(id)
	                .orElse(null);

	        if (existingProfile == null) {
	            return false;
	        }
	        existingProfile.setVehicleType(shipperProfileDTO.getVehicleType());
	        existingProfile.setLicensePlate(shipperProfileDTO.getLicensePlate());
	        existingProfile.setDrivingLicenseNumber(
	                shipperProfileDTO.getDrivingLicenseNumber()
	        );
	        existingProfile.setStatus(shipperProfileDTO.getStatus());
	        existingProfile.setAvailable(shipperProfileDTO.isAvailable());
	        existingProfile.setTotalDeliveries(shipperProfileDTO.getTotalDeliveries());
	        existingProfile.setCurrentLatitude(shipperProfileDTO.getCurrentLatitude());
	        existingProfile.setCurrentLongitude(shipperProfileDTO.getCurrentLongitude());
	        existingProfile.setUpdatedAt(new Date());

	        shipperProfileRepository.save(existingProfile);

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
			shipperProfileRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

}
