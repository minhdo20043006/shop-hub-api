package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.ProductImages;

@Repository
public interface ProductImagesRepository extends JpaRepository<ProductImages, Integer> {

    
    List<ProductImages> findByProduct_Id(Integer productId);

    // Lấy ảnh chính
    ProductImages findByProduct_IdAndIsPrimaryTrue(Integer productId);

    // Reset ảnh chính (chỉ 1 ảnh được primary)
    @Modifying
    @Query("UPDATE ProductImages pi SET pi.isPrimary = false WHERE pi.product.id = :productId")
    void clearPrimaryByProduct(Integer productId);

    // Xóa toàn bộ ảnh theo product (khi product bị soft delete)
    void deleteByProduct_Id(Integer productId);
    
    long countByProduct_Id(Integer productId);

}
