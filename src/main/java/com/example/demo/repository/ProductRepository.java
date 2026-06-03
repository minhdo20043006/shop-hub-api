package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Product;
import com.example.demo.enums.ProductStatus;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("""
			from Product p
			where p.status = com.example.demo.enums.ProductStatus.ACTIVE
			  and lower(p.nameProduct) like lower(concat('%', :keyword, '%'))
			""")
	List<Product> findByKeyword(@Param("keyword") String keyword);

	@Query("from Product where category.id = :categoryId")
	public List<Product> findByCategoryId(@Param("categoryId") Integer categoryId);

	List<Product> findByPriceLessThan(Integer price);

	List<Product> findByPriceGreaterThanEqual(Integer price);

	@Query("from Product where stockQuantity <= :quantity")
	public List<Product> findByStockQuantity(int quantity);

	List<Product> findByStockQuantityBetween(int min, int max);

	
	List<Product> findByStatus(@Param("status") ProductStatus status);

	@Query("from Product where sellerProfile.id = :selllerId")
	List<Product> findBySeller(Integer selllerId);

	List<Product> findByCategory_IdAndStatus(Integer categoryId, ProductStatus status);

	Page<Product> findAllByStatusOrderByCreatedAtDesc(ProductStatus status, Pageable pageable);

	List<Product> findByPriceLessThanAndStatus(double price, ProductStatus status);

	List<Product> findByPriceGreaterThanEqualAndStatus(double price, ProductStatus status);

	@Query("""
			    select oi.product
			    from OrderItem oi
			    group by oi.product
			    order by sum(oi.quantity) desc
			""")
	Page<Product> findBestSellerProducts(Pageable pageable);

	@Query("""
			    from Product p
			    where p.discountPrice is not null
			      and p.discountPrice <= p.price * 0.5
			""")
	List<Product> findDiscountProductsOver50();

	@Query("""
			 from Product p
			 where p.status = :status
			   and (:categoryId is null or p.category.id = :categoryId)
			   and (:minPrice is null or p.price >= :minPrice)
			   and (:maxPrice is null or p.price <= :maxPrice)
			""")
	List<Product> filterByCategoryAndPrice(@Param("categoryId") Integer categoryId, @Param("minPrice") Integer minPrice,
			@Param("maxPrice") Integer maxPrice, @Param("status") ProductStatus status);

	@Query("""
			   SELECT p
			   FROM Product p
			   WHERE p.sellerProfile.id = :sellerId
			     AND p.status IN :statuses
			""")
	List<Product> findProductsBySellerAndStatuses(@Param("sellerId") Integer sellerId,
			@Param("statuses") List<ProductStatus> statuses);

}
