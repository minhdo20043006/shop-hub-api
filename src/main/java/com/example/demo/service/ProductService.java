package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.ProductUpdateStatusDTO;
import com.example.demo.enums.ProductStatus;

public interface ProductService {

	public List<ProductDTO> findAllByStatusActive(ProductStatus status);

	public ProductDTO findAllById(Integer id);

	public List<ProductDTO> findByKeyword(String keyword);

	public List<ProductDTO> findByCategoryId(Integer categoryId);

	public List<ProductDTO> findByPriceRange(String code);

	public List<ProductDTO> findByStockQuantity(int quantity);

	List<ProductDTO> findByStockQuantityBetween(int min, int max);

	List<ProductDTO> findByStatus(ProductStatus status);

	public List<ProductDTO> findDiscountProductsOver50();

	public List<ProductDTO> findNewestProducts(int limit);

	public List<ProductDTO> findBestSellerProducts(int limit);

	public List<ProductDTO> findByCategoryAndPriceCode(Integer categoryId, String code);

	public boolean Create(ProductDTO productDTO);

	public boolean Update(ProductDTO dto);

	public boolean UpdateStatusAdmin(Integer id, ProductUpdateStatusDTO updateStatus);

	public boolean Delete(int id);

	List<ProductDTO> findByCategoryAndStatus(Integer categoryId, ProductStatus status);

	List<ProductDTO> findBySellerAndStatuses(Integer sellerId, List<ProductStatus> statuses);

	public List<ProductDTO> findBySellerId(Integer sellerId);

}
