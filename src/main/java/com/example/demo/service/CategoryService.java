package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.CategoryDTO;
import com.example.demo.dtos.CategoryUpdateStatusDTO;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.ProductUpdateStatusDTO;
import com.example.demo.enums.CategoryStatus;
import com.example.demo.enums.ProductStatus;

public interface CategoryService {
	public List<CategoryDTO> findAllByStatusActive(CategoryStatus status);

	public List<CategoryDTO> findAllForAdmin();

	public boolean Create(CategoryDTO categoryDTO);

	public boolean Update(Integer id, CategoryDTO categoryDTO);

	public boolean UpdateStatusCategory(Integer id, CategoryUpdateStatusDTO statusDto);

	public boolean Delete(int id);
	
	public CategoryDTO findById(Integer id);

}
