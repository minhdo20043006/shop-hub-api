package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.AccountDTO;
import com.example.demo.dtos.SellerProfileDTO;
import com.example.demo.dtos.ShipperProfileDTO;

public interface ShipperProfileService {
	
	public List<ShipperProfileDTO> findAll();
	
	public boolean Create(ShipperProfileDTO shipperProfileDTO);
	
	public boolean Update(Integer id,ShipperProfileDTO shipperProfileDTO);
	
	public boolean Delete(int id);
}
