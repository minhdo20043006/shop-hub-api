package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.ProductReviewDTO;
import com.example.demo.dtos.ProductUpdateStatusDTO;
import com.example.demo.dtos.SellerReviewDTO;
import com.example.demo.enums.ProductStatus;
import com.example.demo.service.ProductReviewService;
import com.example.demo.service.ProductService;
import org.modelmapper.ModelMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;

// sai product ae chu i du lieu nap vao giup tui trong ProductStatus cua phan Enum
@RestController
@RequestMapping({ "api/product" })
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductReviewService productReviewService;

	@GetMapping(value = "all/find-all-product-active", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findAllByStatusActive(ProductStatus status) {
		try {
			return new ResponseEntity<List<ProductDTO>>(productService.findAllByStatusActive(status.ACTIVE),
					HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<ProductDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "all/find-by-id/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> findById(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<ProductDTO>(productService.findAllById(id), HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<ProductDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "all/find-by-keyword", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findByKeyword(@RequestParam String keyword) {
		try {
			return new ResponseEntity<List<ProductDTO>>(productService.findByKeyword(keyword), HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<ProductDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "all/find-by-category-id/{categoryId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findByCategoryId(@PathVariable("categoryId") Integer categoryId) {
		try {
			return new ResponseEntity<List<ProductDTO>>(productService.findByCategoryId(categoryId), HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<ProductDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	// o day dung ma code de tim kiem gia theo kieu duoi 1,3,5,vv nha AE nho vao
	// ProductServiceImpl kiem tra
	@GetMapping(value = "all/find-by-price-range/{code}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findByPriceRange(@PathVariable("code") String code) {
		try {
			return new ResponseEntity<List<ProductDTO>>(productService.findByPriceRange(code), HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<ProductDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	// cua admin
	@GetMapping(value = "ad/find-by-stock-quantity/{quantity}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findByStockQuantity(@PathVariable("quantity") int quantity) {
		try {
			return new ResponseEntity<List<ProductDTO>>(productService.findByStockQuantity(quantity), HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<ProductDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	// cua admin
	@GetMapping(value = "ad/find-by-stock-quantity-between/{min}/{max}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findByStockQuantityBetween(@PathVariable("min") int min,
			@PathVariable("max") int max) {
		try {
			return new ResponseEntity<List<ProductDTO>>(productService.findByStockQuantityBetween(min, max),
					HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<ProductDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	// cua admin cach test
	// localhost:6666/api/product/ad/find-by-status?status=ACTIVE
	@GetMapping(value = "ad/find-by-status", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findByStatus(@RequestParam ProductStatus status) {
		try {
			return new ResponseEntity<List<ProductDTO>>(productService.findByStatus(status), HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<ProductDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "all/find-by-new-product/{limit}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findNewestProducts(@PathVariable("limit") int lmit) {
		try {
			return new ResponseEntity<List<ProductDTO>>(productService.findNewestProducts(lmit), HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<ProductDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "all/find-by-best-seller-product/{limit}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findBestSellerProducts(@PathVariable("limit") int lmit) {
		try {
			return new ResponseEntity<List<ProductDTO>>(productService.findBestSellerProducts(lmit), HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<ProductDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "all/find-by-discount-over-50", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findDiscountProductsOver50() {
		try {
			return new ResponseEntity<List<ProductDTO>>(productService.findDiscountProductsOver50(), HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<ProductDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	// cach test
	// localhost:6666/api/product/all/find-by-category-and-price-code?categoryId=3&code=UNDER_1M
	@GetMapping(value = "all/find-by-category-and-price-code", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findByCategoryAndPriceCode(@RequestParam Integer categoryId,
			@RequestParam String code) {
		try {
			return new ResponseEntity<List<ProductDTO>>(productService.findByCategoryAndPriceCode(categoryId, code),
					HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<ProductDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "se/create", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> CreateProduct(@RequestBody ProductDTO productDTO) {
		try {
			if (productService.Create(productDTO)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "se/update", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> UpdateProduct(@RequestBody ProductDTO productDTO) {
		try {
			if (productService.Update(productDTO)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "ad/update-status-admin", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> UpdateStatusProduct(@RequestBody ProductUpdateStatusDTO updateStatusDto,
			@RequestParam Integer id) {
		try {
			if (productService.UpdateStatusAdmin(id, updateStatusDto)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "as/delete/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		try {
			if (productService.Delete(id)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "review/findall", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductReviewDTO>> findAllReview() {
		try {
			return new ResponseEntity<List<ProductReviewDTO>>(productReviewService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<ProductReviewDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "review/find-by-product-id/{productId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductReviewDTO>> findBySellerIdForReview(
			@PathVariable("productId") Integer productId) {
		try {
			return new ResponseEntity<List<ProductReviewDTO>>(productReviewService.findByProductIdForReview(productId),
					HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<ProductReviewDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "review/create", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> CreateReview(@RequestBody ProductReviewDTO productReviewDTO) {
		try {
			if (productReviewService.Create(productReviewDTO)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "review/update/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> UpdateReview(@RequestBody ProductReviewDTO productReviewDTO,
			@PathVariable("id") Integer id) {
		try {
			if (productReviewService.Update(productReviewDTO)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "review/delete/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteReview(@PathVariable("id") int id) {
		try {
			if (productReviewService.Delete(id)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "all/find-by-category-and-status", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findByCategoryAndStatus(@RequestParam Integer categoryId,
			@RequestParam ProductStatus status) {

		try {
			return new ResponseEntity<>(productService.findByCategoryAndStatus(categoryId, status), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "all/find-by-seller-and-statuses", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findBySellerAndStatuses(@RequestParam Integer sellerId,
			@RequestParam List<ProductStatus> statuses) {

		try {
			return new ResponseEntity<>(productService.findBySellerAndStatuses(sellerId, statuses), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value = "all/find-by-seller-id/{sellerId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findBySellerId(@PathVariable("sellerId") Integer sellerId) {
		try {
			return new ResponseEntity<List<ProductDTO>>(productService.findBySellerId(sellerId), HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<ProductDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

}
