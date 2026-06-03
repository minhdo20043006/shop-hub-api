package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dtos.ProductImagesDTO;

public interface ProductImagesService {

	public boolean uploadImages(Integer productId, List<MultipartFile> files);

	public boolean setPrimaryImage(Integer productImageId);

	public boolean deleteImage(Integer productImageId);

	public List<ProductImagesDTO> findByProduct(Integer productId);
}
