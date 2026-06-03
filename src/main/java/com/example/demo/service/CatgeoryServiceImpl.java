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
import com.example.demo.dtos.CategoryDTO;
import com.example.demo.dtos.CategoryUpdateStatusDTO;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.enums.CategoryStatus;
import com.example.demo.enums.ProductStatus;
import com.example.demo.repository.CategoryRepository;

@Service
public class CatgeoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<CategoryDTO> findAllByStatusActive(CategoryStatus status) {
		List<Category> categories = categoryRepository.findByStatus(status);
		return modelMapper.map(categories, new TypeToken<List<CategoryDTO>>() {
		}.getType());
	}

	@Override
	public List<CategoryDTO> findAllForAdmin() {
		List<Category> categories = categoryRepository.findAll();
		return modelMapper.map(categories, new TypeToken<List<CategoryDTO>>() {
		}.getType());
	}

	@Override
	public boolean Create(CategoryDTO categoryDTO) {
		try {

			Category category = new Category();
			category.setNameCategory(categoryDTO.getNameCategory());
			category.setDescription(categoryDTO.getDescription());
			category.setStatus(CategoryStatus.ACTIVE);
			category.setCreatedAt(new Date());
			category.setUpdatedAt(new Date());
			if (categoryDTO.getCategoryId() != null) {
				Category parent = categoryRepository.findById(categoryDTO.getCategoryId())
						.orElseThrow(() -> new RuntimeException("Category cha không tồn tại"));

				category.setCategory(parent);
			}

			categoryRepository.save(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean Update(Integer id, CategoryDTO categoryDTO) {
		try {
			Category category = categoryRepository.findById(id).orElse(null);
			if (category == null) {
				return false;
			}

			category.setNameCategory(categoryDTO.getNameCategory());
			category.setDescription(categoryDTO.getDescription());
			category.setStatus(categoryDTO.getStatus());
			category.setUpdatedAt(new Date());

			if (categoryDTO.getCategoryId() != null) {
				if (categoryDTO.getCategoryId().equals(id)) {
					throw new IllegalArgumentException("Category không thể làm cha của chính nó");
				}

				Category parent = categoryRepository.findById(categoryDTO.getCategoryId())
						.orElseThrow(() -> new RuntimeException("Category cha không tồn tại"));

				category.setCategory(parent);
			} else {
				category.setCategory(null);
			}

			categoryRepository.save(category);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Delete(int id) {
		try {
			Category category = categoryRepository.findById(id).orElse(null);
			if (category == null) {
				return false;
			}
			category.setStatus(CategoryStatus.INACTIVE);
			category.setUpdatedAt(new Date());

			categoryRepository.save(category);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean UpdateStatusCategory(Integer id, CategoryUpdateStatusDTO statusDto) {
		try {
			Category category = categoryRepository.findById(id).orElse(null);
			if (category == null) {
				return false;
			}

			CategoryStatus status = statusDto.getStatus();

			category.setStatus(status);
			category.setUpdatedAt(new Date());
			categoryRepository.save(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public CategoryDTO findById(Integer id) {
		Category category = categoryRepository.findById(id).get();
		return modelMapper.map(category, CategoryDTO.class);
	}

}
