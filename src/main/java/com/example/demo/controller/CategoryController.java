package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.AccountDTO;
import com.example.demo.dtos.CategoryDTO;
import com.example.demo.dtos.CategoryUpdateStatusDTO;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.ProductUpdateStatusDTO;
import com.example.demo.enums.CategoryStatus;
import com.example.demo.enums.ProductStatus;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping({ "api/category" })
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping(value = "all/find-all-category-active", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryDTO>> findAllByStatusActive(CategoryStatus status) {
		try {
			return new ResponseEntity<List<CategoryDTO>>(categoryService.findAllByStatusActive(status.ACTIVE),
					HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<CategoryDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "ad/find-all", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryDTO>> findAllForadmin() {
		try {
			return new ResponseEntity<List<CategoryDTO>>(categoryService.findAllForAdmin(), HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<CategoryDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "ad/create", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> Create(@RequestBody CategoryDTO categoryDTO) {
		try {
			if (categoryService.Create(categoryDTO)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "ad/update", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> Update(@RequestBody CategoryDTO categoryDTO, @RequestParam Integer id) {
		try {
			if (categoryService.Update(id, categoryDTO)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "ad/update-status-category", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> UpdateStatusCategory(@RequestBody CategoryUpdateStatusDTO updateStatusDto,
			@RequestParam Integer id) {
		try {
			if (categoryService.UpdateStatusCategory(id, updateStatusDto)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("ad/delete/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable("id") int id) {

		boolean result = categoryService.Delete(id);

		if (!result) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@GetMapping(value = "all/find-by-id/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDTO> findById(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<CategoryDTO>(categoryService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<CategoryDTO>(HttpStatus.BAD_REQUEST);
		}
	}
}
