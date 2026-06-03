package com.example.demo.configuration;

import java.util.Date;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.example.demo.dtos.AccountDTO;
import com.example.demo.dtos.AccountInfoDTO;
import com.example.demo.dtos.CartDTO;
import com.example.demo.dtos.CategoryDTO;
import com.example.demo.dtos.NotificationDTO;
import com.example.demo.dtos.OrdersDTO;
import com.example.demo.entities.PaymentMethod;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.ProductImagesDTO;
import com.example.demo.dtos.ProductReviewDTO;
import com.example.demo.dtos.PromotionAccountDTO;
import com.example.demo.dtos.PromotionCategoryDTO;
import com.example.demo.dtos.PromotionConditionDTO;
import com.example.demo.dtos.PromotionDTO;
import com.example.demo.dtos.PromotionProductDTO;
import com.example.demo.dtos.SellerProfileDTO;
import com.example.demo.dtos.SellerReviewDTO;
import com.example.demo.dtos.ShipperProfileDTO;
import com.example.demo.dtos.ShipperReviewDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.Cart;
import com.example.demo.entities.Category;
import com.example.demo.entities.Notification;
import com.example.demo.entities.Orders;
import com.example.demo.entities.Product;
import com.example.demo.entities.ProductImages;
import com.example.demo.entities.ProductReview;
import com.example.demo.entities.Promotion;
import com.example.demo.entities.PromotionAccount;
import com.example.demo.entities.PromotionCategory;
import com.example.demo.entities.PromotionCondition;
import com.example.demo.entities.PromotionProduct;
import com.example.demo.entities.SellerProfile;
import com.example.demo.entities.SellerReview;
import com.example.demo.entities.ShipperProfile;
import com.example.demo.entities.ShipperReview;
import com.example.demo.enums.OrderStatus;
import com.example.demo.enums.PromotionStatus;

@Configuration
public class ModelMaperConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		mapper.addMappings(new PropertyMap<Product, ProductDTO>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setNameProduct(source.getNameProduct());
				map().setPrice(source.getPrice());
				map().setDiscountPrice(source.getDiscountPrice());
				map().setDescription(source.getDescription());
				map().setDimensions(source.getDimensions());
				map().setIsFeatured(source.getIsFeatured());
				map().setIsNew(source.getIsNew());
				map().setPhoto(source.getPhoto());
				map().setSku(source.getSku());
				map().setStatus(source.getStatus());
				map().setStockQuantity(source.getStockQuantity());
				map().setWeight(source.getWeight());
				map().setCategoryId(source.getCategory().getId());
				map().setCategoryName(source.getCategory().getNameCategory());
				map().setCreatedAt(source.getCreatedAt());
				map().setUpdatedAt(source.getUpdatedAt());
				map().setSellerProfileId(source.getSellerProfile().getId());
				map().setSellerProfileName(source.getSellerProfile().getStoreName());
				map().setSellerAccountId(source.getSellerProfile().getAccount().getId());
				map().setAvgRating(source.getAvgRating());
				map().setReviewCount(source.getReviewCount());

			}
		});

		Converter<Integer, Category> converterCategoryIdToCategory = new AbstractConverter<Integer, Category>() {

			@Override
			protected Category convert(Integer categoryId) {
				Category category = new Category();
				category.setId(categoryId);
				return category;
			}
		};

		Converter<Integer, SellerProfile> converterSellerIdToSeller = new AbstractConverter<Integer, SellerProfile>() {

			@Override
			protected SellerProfile convert(Integer sellerProfileId) {
				SellerProfile sellerProfile = new SellerProfile();
				sellerProfile.setId(sellerProfileId);
				return sellerProfile;
			}
		};

		mapper.typeMap(ProductDTO.class, Product.class).addMappings(m -> {
			m.using(converterCategoryIdToCategory).map(ProductDTO::getCategoryId, Product::setCategory);
		});
		mapper.typeMap(ProductDTO.class, Product.class).addMappings(m -> {
			m.using(converterSellerIdToSeller).map(ProductDTO::getSellerProfileId, Product::setSellerProfile);
		});

		mapper.addMappings(new PropertyMap<ProductDTO, Product>() {

			@Override
			protected void configure() {

				map().setNameProduct(source.getNameProduct());
				map().setPrice(source.getPrice());
				map().setDiscountPrice(source.getDiscountPrice());
				map().setDescription(source.getDescription());
				map().setDimensions(source.getDimensions());
				map().setIsFeatured(source.getIsFeatured());
				map().setIsNew(source.getIsNew());
				map().setPhoto(source.getPhoto());
				map().setSku(source.getSku());
				map().setStatus(source.getStatus());
				map().setStockQuantity(source.getStockQuantity());
				map().setWeight(source.getWeight());
				map().setCreatedAt(source.getCreatedAt());
				map().setUpdatedAt(source.getUpdatedAt());
				map().setAvgRating(source.getAvgRating());
				map().setReviewCount(source.getReviewCount());

			}
		});

		mapper.addMappings(new PropertyMap<Account, AccountDTO>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setEmail(source.getEmail());
				map().setAddress(source.getAddress());
				map().setAvatar(source.getAvatar());
				map().setDob(source.getDob());
				map().setFullName(source.getFullName());
				map().setUsername(source.getUsername());
				map().setPassword(source.getPassword());
				map().setPhone(source.getPhone());
				map().setCreatedAt(source.getCreatedAt());
				map().setUpdatedAt(source.getUpdatedAt());
				map().setStatus(source.getStatus());

			}
		});

		mapper.addMappings(new PropertyMap<AccountDTO, Account>() {

			@Override
			protected void configure() {

				map().setId(source.getId());
				map().setEmail(source.getEmail());
				map().setAddress(source.getAddress());
				map().setAvatar(source.getAvatar());
				map().setDob(source.getDob());
				map().setFullName(source.getFullName());
				map().setUsername(source.getUsername());
				map().setPassword(source.getPassword());
				map().setPhone(source.getPhone());
				map().setCreatedAt(source.getCreatedAt());
				map().setUpdatedAt(source.getUpdatedAt());
				map().setStatus(source.getStatus());

			}
		});

		mapper.addMappings(new PropertyMap<Account, AccountInfoDTO>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setEmail(source.getEmail());
				map().setFullName(source.getFullName());
				map().setUsername(source.getUsername());

			}
		});

		mapper.addMappings(new PropertyMap<AccountInfoDTO, Account>() {

			@Override
			protected void configure() {

				map().setId(source.getId());
				map().setEmail(source.getEmail());
				map().setFullName(source.getFullName());
				map().setUsername(source.getUsername());

			}
		});

		mapper.addMappings(new PropertyMap<SellerProfile, SellerProfileDTO>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setStoreName(source.getStoreName());
				map().setStoreAddress(source.getStoreAddress());
				map().setTaxCode(source.getTaxCode());
				map().setBusinessLicenseNumber(source.getBusinessLicenseNumber());
				map().setLogo(source.getLogo());
				map().setApprovedStatus(source.getApprovedStatus());
				map().setAccountId(source.getAccount().getId());
				map().setAccountName(source.getAccount().getFullName());
				map().setDescription(source.getDescription());
				map().setCreatedAt(source.getCreatedAt());
				map().setUpdatedAt(source.getUpdatedAt());
				map().setAvgRating(source.getAvgRating());
				map().setReviewCount(source.getReviewCount());

			}
		});

		Converter<Integer, Account> converterAccountIdToAccount = new AbstractConverter<Integer, Account>() {

			@Override
			protected Account convert(Integer accountId) {
				Account account = new Account();
				account.setId(accountId);
				return account;
			}
		};

		Converter<Integer, SellerProfile> converterSellerProfleIdToSellerProfile = new AbstractConverter<Integer, SellerProfile>() {

			@Override
			protected SellerProfile convert(Integer sellerProfileId) {
				SellerProfile sellerProfile = new SellerProfile();
				sellerProfile.setId(sellerProfileId);
				return sellerProfile;
			}
		};
		Converter<Integer, ShipperProfile> converterShipperProfileIdToShipperProfile = new AbstractConverter<Integer, ShipperProfile>() {

			@Override
			protected ShipperProfile convert(Integer shipperProfileId) {
				ShipperProfile ShipperProfile = new ShipperProfile();
				ShipperProfile.setId(shipperProfileId);
				return ShipperProfile;
			}
		};

		mapper.typeMap(SellerProfileDTO.class, SellerProfile.class).addMappings(m -> {
			m.using(converterAccountIdToAccount).map(SellerProfileDTO::getAccountId, SellerProfile::setAccount);
		});

		mapper.addMappings(new PropertyMap<SellerProfileDTO, SellerProfile>() {

			@Override
			protected void configure() {

				map().setId(source.getId());
				map().setStoreName(source.getStoreName());
				map().setStoreAddress(source.getStoreAddress());
				map().setTaxCode(source.getTaxCode());
				map().setBusinessLicenseNumber(source.getBusinessLicenseNumber());
				map().setLogo(source.getLogo());
				map().setApprovedStatus(source.getApprovedStatus());
				map().setDescription(source.getDescription());
				map().setCreatedAt(source.getCreatedAt());
				map().setUpdatedAt(source.getUpdatedAt());
				map().setAvgRating(source.getAvgRating());
				map().setReviewCount(source.getReviewCount());

			}
		});

		mapper.addMappings(new PropertyMap<ShipperProfile, ShipperProfileDTO>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setAccountId(source.getAccount().getId());
				map().setAccountName(source.getAccount().getFullName());
				map().setAvailable(source.isAvailable());
				map().setCreatedAt(source.getCreatedAt());
				map().setUpdatedAt(source.getUpdatedAt());
				map().setCurrentLatitude(source.getCurrentLatitude());
				map().setCurrentLongitude(source.getCurrentLongitude());
				map().setDrivingLicenseNumber(source.getDrivingLicenseNumber());
				map().setLicensePlate(source.getLicensePlate());
				map().setStatus(source.getStatus());
				map().setTotalDeliveries(source.getTotalDeliveries());
				map().setVehicleType(source.getVehicleType());
				map().setAvgRating(source.getAvgRating());
				map().setReviewCount(source.getReviewCount());

			}
		});

		mapper.typeMap(ShipperProfileDTO.class, ShipperProfile.class).addMappings(m -> {
			m.using(converterAccountIdToAccount).map(ShipperProfileDTO::getAccountId, ShipperProfile::setAccount);
		});

		mapper.addMappings(new PropertyMap<ShipperProfileDTO, ShipperProfile>() {

			@Override
			protected void configure() {

				map().setId(source.getId());
				map().setAvailable(source.isAvailable());
				map().setCreatedAt(source.getCreatedAt());
				map().setUpdatedAt(source.getUpdatedAt());
				map().setCurrentLatitude(source.getCurrentLatitude());
				map().setCurrentLongitude(source.getCurrentLongitude());
				map().setDrivingLicenseNumber(source.getDrivingLicenseNumber());
				map().setLicensePlate(source.getLicensePlate());
				map().setStatus(source.getStatus());
				map().setTotalDeliveries(source.getTotalDeliveries());
				map().setVehicleType(source.getVehicleType());
				map().setAvgRating(source.getAvgRating());
				map().setReviewCount(source.getReviewCount());

			}
		});

		mapper.addMappings(new PropertyMap<SellerReview, SellerReviewDTO>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setAccountId(source.getAccount().getId());
				map().setAccountName(source.getAccount().getFullName());
				map().setSellerProfileId(source.getSellerProfile().getId());
				map().setSellerProfileName(source.getSellerProfile().getStoreName());
				map().setRating(source.getRating());
				map().setCommentReview(source.getCommentReview());
				map().setCreatedAt(source.getCreatedAt());

			}
		});

		mapper.typeMap(SellerReviewDTO.class, SellerReview.class).addMappings(m -> {
			m.using(converterAccountIdToAccount).map(SellerReviewDTO::getAccountId, SellerReview::setAccount);
		});

		mapper.typeMap(SellerReviewDTO.class, SellerReview.class).addMappings(m -> {
			m.using(converterSellerProfleIdToSellerProfile).map(SellerReviewDTO::getSellerProfileId,
					SellerReview::setSellerProfile);
		});

		mapper.addMappings(new PropertyMap<SellerReviewDTO, SellerReview>() {

			@Override
			protected void configure() {

				map().setId(source.getId());
				map().setRating(source.getRating());
				map().setCommentReview(source.getCommentReview());
				map().setCreatedAt(source.getCreatedAt());

			}
		});

		mapper.addMappings(new PropertyMap<ShipperReview, ShipperReviewDTO>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setAccountId(source.getAccount().getId());
				map().setAccountName(source.getAccount().getFullName());
				map().setShipperProfileId(source.getShipperProfile().getId());
				map().setRating(source.getRating());
				map().setCommentReview(source.getCommentReview());
				map().setCreatedAt(source.getCreatedAt());

			}
		});

		mapper.typeMap(SellerReviewDTO.class, SellerReview.class).addMappings(m -> {
			m.using(converterAccountIdToAccount).map(SellerReviewDTO::getAccountId, SellerReview::setAccount);
		});

		mapper.typeMap(ShipperReviewDTO.class, ShipperReview.class).addMappings(m -> {
			m.using(converterShipperProfileIdToShipperProfile).map(ShipperReviewDTO::getShipperProfileId,
					ShipperReview::setShipperProfile);
		});

		mapper.addMappings(new PropertyMap<ShipperReviewDTO, ShipperReview>() {

			@Override
			protected void configure() {

				map().setId(source.getId());

				map().setRating(source.getRating());
				map().setCommentReview(source.getCommentReview());
				map().setCreatedAt(source.getCreatedAt());

			}
		});

		Converter<Orders, Integer> orderToOrderId = ctx -> ctx.getSource() == null ? null : ctx.getSource().getId();

		Converter<Product, Integer> productToProductId = ctx -> ctx.getSource() == null ? null
				: ctx.getSource().getId();

		Converter<Category, Integer> categoryToCategoryId = ctx -> ctx.getSource() == null ? null
				: ctx.getSource().getId();

		Converter<Promotion, Integer> promotionToPromotionId = ctx -> ctx.getSource() == null ? null
				: ctx.getSource().getId();

		mapper.addMappings(new PropertyMap<Notification, NotificationDTO>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setAccountId(source.getAccount().getId());

				map().setReceiverType(source.getReceiverType());
				map().setTypeNotification(source.getTypeNotification());

				map().setTitleNotification(source.getTitleNotification());
				map().setMessageNotification(source.getMessageNotification());
				map().setRead(source.isIsRead());
				map().setCreatedAt(source.getCreatedAt());

				using(orderToOrderId).map(source.getOrder(), destination.getOrderId());

				using(productToProductId).map(source.getProduct(), destination.getProductId());

				using(categoryToCategoryId).map(source.getCategory(), destination.getCategoryId());

				using(promotionToPromotionId).map(source.getPromotion(), destination.getPromotionId());
			}
		});

		Converter<Integer, Orders> converterOrderIdToOrder = new AbstractConverter<Integer, Orders>() {

			@Override
			protected Orders convert(Integer orderId) {
				Orders order = new Orders();
				order.setId(orderId);
				return order;
			}
		};

		Converter<Integer, Product> converterProductIdToProduct = new AbstractConverter<Integer, Product>() {

			@Override
			protected Product convert(Integer productId) {
				Product product = new Product();
				product.setId(productId);
				return product;
			}
		};

		Converter<Integer, Promotion> converterPromotionIdToPromotion = new AbstractConverter<Integer, Promotion>() {

			@Override
			protected Promotion convert(Integer promotionId) {
				Promotion promotion = new Promotion();
				promotion.setId(promotionId);
				return promotion;
			}
		};

		mapper.typeMap(NotificationDTO.class, Notification.class).addMappings(m -> {
			m.using(converterAccountIdToAccount).map(NotificationDTO::getAccountId, Notification::setAccount);
		});

		mapper.typeMap(NotificationDTO.class, Notification.class).addMappings(m -> {
			m.using(converterCategoryIdToCategory).map(NotificationDTO::getCategoryId, Notification::setCategory);
		});

		mapper.typeMap(NotificationDTO.class, Notification.class).addMappings(m -> {
			m.using(converterOrderIdToOrder).map(NotificationDTO::getOrderId, Notification::setOrder);
		});

		mapper.typeMap(NotificationDTO.class, Notification.class).addMappings(m -> {
			m.using(converterProductIdToProduct).map(NotificationDTO::getProductId, Notification::setProduct);
		});

		mapper.typeMap(NotificationDTO.class, Notification.class).addMappings(m -> {
			m.using(converterPromotionIdToPromotion).map(NotificationDTO::getPromotionId, Notification::setPromotion);
		});

		mapper.addMappings(new PropertyMap<NotificationDTO, Notification>() {

			@Override
			protected void configure() {
				map().setReceiverType(source.getReceiverType());
				map().setTypeNotification(source.getTypeNotification());
				map().setTitleNotification(source.getTitleNotification());
				map().setMessageNotification(source.getMessageNotification());
				map().setIsRead(source.isRead());
				map().setCreatedAt(source.getCreatedAt());
			}
		});

		mapper.addMappings(new PropertyMap<Category, CategoryDTO>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				if (source.getCategory() != null) {
					map().setCategoryId(source.getCategory().getId());
					map().setCategoryNameOrigin(source.getCategory().getNameCategory());
				}
				map().setDescription(source.getDescription());

				map().setCreatedAt(source.getCreatedAt());
				map().setStatus(source.getStatus());
				map().setUpdatedAt(source.getUpdatedAt());
			}
		});

		mapper.typeMap(CategoryDTO.class, Category.class).addMappings(m -> {
			m.using(converterCategoryIdToCategory).map(CategoryDTO::getCategoryId, Category::setCategory);
		});

		mapper.addMappings(new PropertyMap<CategoryDTO, Category>() {

			@Override
			protected void configure() {
				map().setDescription(source.getDescription());
				map().setCreatedAt(source.getCreatedAt());
				map().setStatus(source.getStatus());
				map().setUpdatedAt(source.getUpdatedAt());
			}
		});

		mapper.addMappings(new PropertyMap<Promotion, PromotionDTO>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setNamePromotion(source.getNamePromotion());
				map().setDescription(source.getDescription());
				map().setDiscountType(source.getDiscountType());
				map().setDiscountValue(source.getDiscountValue());
				map().setStartDate(source.getStartDate());
				map().setEndDate(source.getEndDate());
				map().setStatusPromotion(source.getStatusPromotion());
				map().setMaxDiscount(source.getMaxDiscount());
				map().setMinOrderValue(source.getMinOrderValue());
				map().setQuantityPromotion(source.getQuantityPromotion());
			}
		});

		mapper.addMappings(new PropertyMap<PromotionDTO, Promotion>() {

			@Override
			protected void configure() {
				map().setNamePromotion(source.getNamePromotion());
				map().setDescription(source.getDescription());
				map().setDiscountType(source.getDiscountType());
				map().setDiscountValue(source.getDiscountValue());
				map().setStartDate(source.getStartDate());
				map().setEndDate(source.getEndDate());
				map().setStatusPromotion(source.getStatusPromotion());
				map().setMaxDiscount(source.getMaxDiscount());
				map().setMinOrderValue(source.getMinOrderValue());
				map().setQuantityPromotion(source.getQuantityPromotion());
			}
		});

		mapper.addMappings(new PropertyMap<PromotionAccount, PromotionAccountDTO>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setAccountId(source.getAccount().getId());
				map().setAccountName(source.getAccount().getUsername());
				map().setPromotionId(source.getPromotion().getId());
				map().setPromotionName(source.getPromotion().getNamePromotion());

			}
		});

		mapper.typeMap(PromotionAccountDTO.class, PromotionAccount.class).addMappings(m -> {
			m.using(converterAccountIdToAccount).map(PromotionAccountDTO::getAccountId, PromotionAccount::setAccount);
		});
		mapper.typeMap(PromotionAccountDTO.class, PromotionAccount.class).addMappings(m -> {
			m.using(converterPromotionIdToPromotion).map(PromotionAccountDTO::getPromotionId,
					PromotionAccount::setPromotion);
		});

		mapper.addMappings(new PropertyMap<PromotionProduct, PromotionProductDTO>() {
			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setPromotionId(source.getPromotion().getId());
				map().setPromotionName(source.getPromotion().getNamePromotion());
				map().setProductId(source.getProduct().getId());
				map().setProductName(source.getProduct().getNameProduct());
			}
		});

		mapper.typeMap(PromotionProductDTO.class, PromotionProduct.class).addMappings(m -> {
			m.using(converterPromotionIdToPromotion).map(PromotionProductDTO::getPromotionId,
					PromotionProduct::setPromotion);
		});

		mapper.typeMap(PromotionProductDTO.class, PromotionProduct.class).addMappings(m -> {
			m.using(converterProductIdToProduct).map(PromotionProductDTO::getProductId, PromotionProduct::setProduct);
		});

		mapper.addMappings(new PropertyMap<PromotionCategory, PromotionCategoryDTO>() {
			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setPromotionId(source.getPromotion().getId());
				map().setPromotionName(source.getPromotion().getNamePromotion());
				map().setCategoryId(source.getCategory().getId());
				map().setCategoryName(source.getCategory().getNameCategory());
			}
		});

		mapper.typeMap(PromotionCategoryDTO.class, PromotionCategory.class).addMappings(m -> {
			m.using(converterPromotionIdToPromotion).map(PromotionCategoryDTO::getPromotionId,
					PromotionCategory::setPromotion);
		});

		mapper.typeMap(PromotionCategoryDTO.class, PromotionCategory.class).addMappings(m -> {
			m.using(converterCategoryIdToCategory).map(PromotionCategoryDTO::getCategoryId,
					PromotionCategory::setCategory);
		});

		// ENTITY -> DTO
		mapper.addMappings(new PropertyMap<PromotionCondition, PromotionConditionDTO>() {
			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setPromotionId(source.getPromotion().getId());
				map().setConditionType(source.getConditionType());
				map().setConditionValue(source.getConditionValue());
			}
		});

		mapper.typeMap(PromotionConditionDTO.class, PromotionCondition.class).addMappings(m -> {
			m.using(converterPromotionIdToPromotion).map(PromotionConditionDTO::getPromotionId,
					PromotionCondition::setPromotion);
		});

		mapper.addMappings(new PropertyMap<ProductImages, ProductImagesDTO>() {
			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setProductId(source.getProduct().getId());
				map().setImage(source.getImage());
				map().setPrimary(source.isIsPrimary());
				map().setCreatedAt(source.getCreatedAt());
				map().setUpdatedAt(source.getUpdatedAt());
			}
		});

		mapper.typeMap(ProductImagesDTO.class, ProductImages.class).addMappings(m -> {
			m.using(converterProductIdToProduct).map(ProductImagesDTO::getProductId, ProductImages::setProduct);
		});

		mapper.addMappings(new PropertyMap<ProductImagesDTO, ProductImages>() {

			@Override
			protected void configure() {
				map().setImage(source.getImage());
				map().setIsPrimary(source.isPrimary());
				map().setCreatedAt(source.getCreatedAt());
				map().setUpdatedAt(source.getUpdatedAt());
			}
		});

		mapper.addMappings(new PropertyMap<ProductReview, ProductReviewDTO>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setAccountId(source.getAccount().getId());
				map().setAccountName(source.getAccount().getFullName());
				map().setProductId(source.getProduct().getId());
				map().setProductName(source.getProduct().getNameProduct());
				map().setRating(source.getRating());
				map().setCommentReview(source.getCommentReview());
				map().setCreatedAt(source.getCreatedAt());

			}
		});

		mapper.typeMap(ProductReviewDTO.class, ProductReview.class).addMappings(m -> {
			m.using(converterAccountIdToAccount).map(ProductReviewDTO::getAccountId, ProductReview::setAccount);
		});

		mapper.typeMap(ProductReviewDTO.class, ProductReview.class).addMappings(m -> {
			m.using(converterProductIdToProduct).map(ProductReviewDTO::getProductId, ProductReview::setProduct);
		});

		mapper.addMappings(new PropertyMap<ProductReviewDTO, ProductReview>() {

			@Override
			protected void configure() {

				map().setId(source.getId());
				map().setRating(source.getRating());
				map().setCommentReview(source.getCommentReview());
				map().setCreatedAt(source.getCreatedAt());

			}
		});

		mapper.addMappings(new PropertyMap<Cart, CartDTO>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setAccountId(source.getAccount().getId());
				map().setAccountName(source.getAccount().getFullName());
				map().setProductId(source.getProduct().getId());
				map().setProductName(source.getProduct().getNameProduct());
				map().setProductPhoto(source.getProduct().getPhoto());
				map().setPrice(source.getPrice());
				map().setCreatedAt(source.getCreatedAt());
				map().setDiscount(source.getDiscount());
				map().setQuantityCart(source.getQuantityCart());
				map().setTotalPrice(source.getTotalPrice());
				map().setUpdatedAt(source.getUpdatedAt());

			}
		});

		mapper.typeMap(CartDTO.class, Cart.class).addMappings(m -> {
			m.using(converterAccountIdToAccount).map(CartDTO::getAccountId, Cart::setAccount);
		});

		mapper.typeMap(CartDTO.class, Cart.class).addMappings(m -> {
			m.using(converterProductIdToProduct).map(CartDTO::getProductId, Cart::setProduct);
		});

		mapper.addMappings(new PropertyMap<CartDTO, Cart>() {

			@Override
			protected void configure() {

				map().setPrice(source.getPrice());
				map().setCreatedAt(source.getCreatedAt());
				map().setDiscount(source.getDiscount());
				map().setQuantityCart(source.getQuantityCart());
				map().setTotalPrice(source.getTotalPrice());
				map().setUpdatedAt(source.getUpdatedAt());

			}
		});

		mapper.getConfiguration()
				.setSkipNullEnabled(true)
				.setCollectionsMergeEnabled(false);

		TypeMap<Orders, OrdersDTO> orderToDto = mapper.createTypeMap(Orders.class, OrdersDTO.class);

		orderToDto.addMappings(m -> {

			m.map(Orders::getId, OrdersDTO::setId);

			m.map(src -> src.getAccount().getId(), OrdersDTO::setAccountId);
			m.map(src -> src.getAccount().getFullName(), OrdersDTO::setAccountFullName);

			m.map(src -> src.getPaymentMethod().getId(), OrdersDTO::setPaymentMethodId);
			m.map(src -> src.getPaymentMethod().getPaymentName(), OrdersDTO::setPaymentMethodName);

			m.map(Orders::getStatusOrder, OrdersDTO::setOrderStatus);
			m.map(Orders::getTotalAmount, OrdersDTO::setTotalAmount);
			m.map(Orders::getShippingAddress, OrdersDTO::setShippingAddress);
			m.map(Orders::getCreatedAt, OrdersDTO::setCreatedAt);
			m.map(Orders::getUpdatedAt, OrdersDTO::setUpdatedAt);

			m.skip(OrdersDTO::setOrderItems);
		});

		Converter<Integer, PaymentMethod> converterPaymentMethodIdToPaymentMethod = ctx -> {
			if (ctx.getSource() == null)
				return null;
			PaymentMethod pm = new PaymentMethod();
			pm.setId(ctx.getSource());
			return pm;
		};

		TypeMap<OrdersDTO, Orders> dtoToOrder = mapper.createTypeMap(OrdersDTO.class, Orders.class);

		dtoToOrder.addMappings(m -> {

			m.using(converterAccountIdToAccount)
					.map(OrdersDTO::getAccountId, Orders::setAccount);

			m.using(converterPaymentMethodIdToPaymentMethod)
					.map(OrdersDTO::getPaymentMethodId, Orders::setPaymentMethod);

			m.skip(Orders::setOrderItems);
		});
		mapper.addMappings(new PropertyMap<OrdersDTO, Orders>() {
			@Override
			protected void configure() {
				when(ctx -> source.getOrderStatus() == null)
						.map(OrderStatus.CREATED, destination.getStatusOrder());
			}
		});

		return mapper;
	}
}
