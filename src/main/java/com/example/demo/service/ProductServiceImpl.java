package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.ProductUpdateStatusDTO;

import com.example.demo.entities.Account;
import com.example.demo.entities.Product;
import com.example.demo.entities.Role;
import com.example.demo.entities.RoleAccount;

import com.example.demo.enums.ProductStatus;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ProductDTO> findAllByStatusActive(ProductStatus status) {
		List<Product> products = productRepository.findByStatus(status);
		return modelMapper.map(products, new TypeToken<List<ProductDTO>>() {
		}.getType());
	}

	@Override
	public List<ProductDTO> findByKeyword(String keyword) {
		List<Product> products = productRepository.findByKeyword(keyword);
		return modelMapper.map(products, new TypeToken<List<ProductDTO>>() {
		}.getType());
	}

	@Override
	public List<ProductDTO> findByCategoryId(Integer categoryId) {
		List<Product> products = productRepository.findByCategoryId(categoryId);
		return modelMapper.map(products, new TypeToken<List<ProductDTO>>() {
		}.getType());
	}

	@Override
	public List<ProductDTO> findByPriceRange(String code) {

		List<Product> products;

		switch (code) {
			case "UNDER_1M":
				products = productRepository.findByPriceLessThanAndStatus(1_000_000, ProductStatus.ACTIVE);
				break;

			case "UNDER_3M":
				products = productRepository.findByPriceLessThanAndStatus(3_000_000, ProductStatus.ACTIVE);
				break;

			case "UNDER_5M":
				products = productRepository.findByPriceLessThanAndStatus(5_000_000, ProductStatus.ACTIVE);
				break;

			case "UNDER_10M":
				products = productRepository.findByPriceLessThanAndStatus(10_000_000, ProductStatus.ACTIVE);
				break;

			case "UNDER_15M":
				products = productRepository.findByPriceLessThanAndStatus(15_000_000, ProductStatus.ACTIVE);
				break;

			case "ABOVE_50M":
				products = productRepository.findByPriceGreaterThanEqualAndStatus(50_000_000, ProductStatus.ACTIVE);
				break;

			default:
				throw new IllegalArgumentException("Invalid price range");
		}

		return modelMapper.map(products, new TypeToken<List<ProductDTO>>() {
		}.getType());
	}

	// tim kiem theo quantity gom findByStockQuantity findByStockQuantityBetween
	@Override
	public List<ProductDTO> findByStockQuantity(int quantity) {
		List<Product> products = productRepository.findByStockQuantity(quantity);
		return modelMapper.map(products, new TypeToken<List<ProductDTO>>() {
		}.getType());
	}

	@Override
	public List<ProductDTO> findByStockQuantityBetween(int min, int max) {

		List<Product> products = productRepository.findByStockQuantityBetween(min, max);

		return modelMapper.map(products, new TypeToken<List<ProductDTO>>() {
		}.getType());
	}

	// (ACTIVE, OUT_OF_STOCK, BANNED)
	@Override
	public List<ProductDTO> findByStatus(ProductStatus status) {

		List<Product> products = productRepository.findByStatus(status);

		System.out.println("SIZE = " + products.size());
		products.forEach(p -> System.out.println("ID = " + p.getId()));
		return modelMapper.map(products, new TypeToken<List<ProductDTO>>() {
		}.getType());
	}

	@Override
	public List<ProductDTO> findNewestProducts(int limit) {
		Pageable pageable = PageRequest.of(0, limit);
		Page<Product> page = productRepository.findAllByStatusOrderByCreatedAtDesc(ProductStatus.ACTIVE, pageable);

		return modelMapper.map(page.getContent(), new TypeToken<List<ProductDTO>>() {
		}.getType());
	}

	@Override
	public List<ProductDTO> findBestSellerProducts(int limit) {
		Pageable pageable = PageRequest.of(0, limit);
		Page<Product> page = productRepository.findBestSellerProducts(pageable);

		return modelMapper.map(page.getContent(), new TypeToken<List<ProductDTO>>() {
		}.getType());
	}

	@Override
	public List<ProductDTO> findDiscountProductsOver50() {
		List<Product> products = productRepository.findDiscountProductsOver50();
		return modelMapper.map(products, new TypeToken<List<ProductDTO>>() {
		}.getType());
	}

	@Override
	public List<ProductDTO> findByCategoryAndPriceCode(Integer categoryId, String code) {
		Integer minPrice = null;
		Integer maxPrice = null;

		switch (code) {
			case "UNDER_1M" -> maxPrice = 1_000_000;
			case "UNDER_3M" -> maxPrice = 3_000_000;
			case "UNDER_5M" -> maxPrice = 5_000_000;
			case "UNDER_10M" -> maxPrice = 10_000_000;
			case "UNDER_15M" -> maxPrice = 15_000_000;
			case "ABOVE_50M" -> minPrice = 50_000_000;
			default -> throw new IllegalArgumentException("Invalid price range");
		}

		List<Product> products = productRepository.filterByCategoryAndPrice(categoryId, minPrice, maxPrice,
				ProductStatus.ACTIVE);

		return modelMapper.map(products, new TypeToken<List<ProductDTO>>() {
		}.getType());
	}

	@Override
	@Transactional
	public boolean Create(ProductDTO productDTO) {

		Product product = modelMapper.map(productDTO, Product.class);

		product.setPhoto("default.png");
		product.setCreatedAt(new Date());
		product.setUpdatedAt(new Date());
		product.setStatus(ProductStatus.PENDING);
		productRepository.save(product);

		return true;

	}

	@Override
	@Transactional
	public boolean Update(ProductDTO dto) {

		Product product = productRepository.findById(dto.getId())
				.orElseThrow(() -> new RuntimeException("Product not found"));

		if (dto.getNameProduct() != null) {
			product.setNameProduct(dto.getNameProduct());
		}

		if (dto.getDescription() != null) {
			product.setDescription(dto.getDescription());
		}

		if (dto.getPrice() != 0) {
			product.setPrice(dto.getPrice());
		}

		if (dto.getPhoto() != null) {
			product.setPhoto(dto.getPhoto());
		}

		if (dto.getStockQuantity() != 0) {
			product.setStockQuantity(dto.getStockQuantity());

			if (dto.getStockQuantity() == 0) {
				product.setStatus(ProductStatus.OUT_OF_STOCK);
			}
		}

		if (dto.getSku() != null) {
			product.setSku(dto.getSku());
		}

		if (dto.getDiscountPrice() != 0) {
			product.setDiscountPrice(dto.getDiscountPrice());
		}

		if (dto.getWeight() != null) {
			product.setWeight(dto.getWeight());
		}

		if (dto.getDimensions() != null) {
			product.setDimensions(dto.getDimensions());
		}

		if (dto.getIsFeatured() != null) {
			product.setIsFeatured(dto.getIsFeatured());
		}

		if (dto.getIsNew() != null) {
			product.setIsNew(dto.getIsNew());
		}
		product.setUpdatedAt(new Date());
		product.setStatus(ProductStatus.PENDING);
		return true;
	}

	@Override
	@Transactional
	public boolean UpdateStatusAdmin(Integer id, ProductUpdateStatusDTO updateStatus) {
		Product product = productRepository.findById(id).orElse(null);
		if (product == null)
			return false;
		ProductStatus status = updateStatus.getStatus();

		if (status == ProductStatus.DRAFT) {
			throw new IllegalArgumentException("Admin không set DRAFT");
		}
		product.setStatus(status);
		product.setUpdatedAt(new Date());
		productRepository.save(product);
		return true;
	}

	@Override
	public boolean Delete(int id) {
		Optional<Product> opt = productRepository.findById(id);

		if (opt.isEmpty()) {
			return false;
		}

		Product product = opt.get();

		if (product.getStatus() == ProductStatus.INACTIVE) {
			return false;
		}

		product.setStatus(ProductStatus.INACTIVE);
		productRepository.save(product);

		return true;
	}

	@Override
	public ProductDTO findAllById(Integer id) {
		Product product = productRepository.findById(id).get();
		return modelMapper.map(product, ProductDTO.class);
	}

	@Override
	public List<ProductDTO> findByCategoryAndStatus(Integer categoryId, ProductStatus status) {

		return productRepository.findByCategory_IdAndStatus(categoryId, status).stream()
				.map(p -> modelMapper.map(p, ProductDTO.class)).toList();
	}

	@Override
	public List<ProductDTO> findBySellerAndStatuses(Integer sellerId, List<ProductStatus> statuses) {

		List<Product> products = productRepository.findProductsBySellerAndStatuses(sellerId, statuses);

		return modelMapper.map(products, new TypeToken<List<ProductDTO>>() {
		}.getType());
	}

	@Override
	public List<ProductDTO> findBySellerId(Integer sellerId) {
		List<Product> products = productRepository.findBySeller(sellerId);
		return modelMapper.map(products, new TypeToken<List<ProductDTO>>() {
		}.getType());
	}

}
