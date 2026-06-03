package com.example.demo.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dtos.ProductImagesDTO;
import com.example.demo.entities.Product;
import com.example.demo.entities.ProductImages;
import com.example.demo.repository.ProductImagesRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductImagesService;

@Service
@Transactional
public class ProductImagesServiceImpl implements ProductImagesService {

    @Autowired
    private ProductImagesRepository productImagesRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final String UPLOAD_DIR = "C:/uploads/products/";

    @Override
    public boolean uploadImages(Integer productId, List<MultipartFile> files) {
        try {
            Product product = productRepository.findById(productId).orElse(null);
            if (product == null || files == null || files.isEmpty()) {
                return false;
            }

            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            boolean isFirstImage = productImagesRepository
                    .findByProduct_Id(productId)
                    .isEmpty();

            for (MultipartFile file : files) {
                if (file.isEmpty()) continue;

                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                File dest = new File(UPLOAD_DIR + fileName);
                file.transferTo(dest);

                ProductImages image = new ProductImages();
                image.setProduct(product);
                image.setImage(fileName);
                image.setIsPrimary(isFirstImage); // ảnh đầu tiên làm primary
                image.setCreatedAt(new Date());
                image.setUpdatedAt(new Date());

                productImagesRepository.save(image);
                isFirstImage = false;
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean setPrimaryImage(Integer productImageId) {
        Optional<ProductImages> opt = productImagesRepository.findById(productImageId);
        if (opt.isEmpty()) {
            return false;
        }

        ProductImages image = opt.get();
        Integer productId = image.getProduct().getId();

        productImagesRepository.clearPrimaryByProduct(productId);

        image.setIsPrimary(true);
        image.setUpdatedAt(new Date());
        productImagesRepository.save(image);

        return true;
    }

  
    @Override
    public boolean deleteImage(Integer productImageId) {
        Optional<ProductImages> opt = productImagesRepository.findById(productImageId);
        if (opt.isEmpty()) {
            return false;
        }

        ProductImages image = opt.get();
        Integer productId = image.getProduct().getId();
        boolean wasPrimary = image.isIsPrimary();

        productImagesRepository.delete(image);

        //  xóa ảnh primary → set ảnh khác làm primary
        if (wasPrimary) {
            List<ProductImages> images = productImagesRepository.findByProduct_Id(productId);
            if (!images.isEmpty()) {
                ProductImages newPrimary = images.get(0);
                newPrimary.setIsPrimary(true);
                newPrimary.setUpdatedAt(new Date());
                productImagesRepository.save(newPrimary);
            }
        }

        return true;
    }


    @Override
    public List<ProductImagesDTO> findByProduct(Integer productId) {
        List<ProductImages> images = productImagesRepository.findByProduct_Id(productId);

        return modelMapper.map(
                images,
                new TypeToken<List<ProductImagesDTO>>() {}.getType()
        );
    }
}
