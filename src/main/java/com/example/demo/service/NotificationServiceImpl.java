package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.NotificationDTO;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.Notification;
import com.example.demo.entities.Product;
import com.example.demo.enums.NotificationType;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.PromotionRepository;
import com.example.demo.repository.RoleAccountRepository;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private PromotionRepository promotionRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RoleAccountRepository roleAccountRepository;

	
	private void validateTarget(NotificationDTO dto) {

	    switch (dto.getTypeNotification()) {

	        case PRODUCT -> {
	            if (dto.getProductId() == null)
	                throw new RuntimeException("ProductId required");
	        }

	        case CATEGORY -> {
	            if (dto.getCategoryId() == null)
	                throw new RuntimeException("CategoryId required");
	        }

	        case ORDER -> {
	            if (dto.getOrderId() == null)
	                throw new RuntimeException("OrderId required");
	        }

	        case PROMOTION -> {
	            if (dto.getPromotionId() == null)
	                throw new RuntimeException("PromotionId required");
	        }
	    }
	}
	
	private Notification buildBaseNotification(NotificationDTO dto) {

	    Notification n = new Notification();

	    n.setReceiverType(dto.getReceiverType());
	    n.setTypeNotification(dto.getTypeNotification());
	    n.setTitleNotification(dto.getTitleNotification());
	    n.setMessageNotification(dto.getMessageNotification());
	    n.setIsRead(false);
	    n.setCreatedAt(new Date());

	    if (dto.getProductId() != null)
	        n.setProduct(productRepository.findById(dto.getProductId()).orElse(null));

	    if (dto.getCategoryId() != null)
	        n.setCategory(categoryRepository.findById(dto.getCategoryId()).orElse(null));

	    if (dto.getOrderId() != null)
	        n.setOrder(orderRepository.findById(dto.getOrderId()).orElse(null));

	    if (dto.getPromotionId() != null)
	        n.setPromotion(promotionRepository.findById(dto.getPromotionId()).orElse(null));

	    return n;
	}

	
	
	@Override
	public boolean create(NotificationDTO dto) {
	    try {

	        List<Account> receivers = new ArrayList<>();

	        // 1️⃣ Xác định danh sách account nhận
	        if (dto.getAccountId() != null) {
	            // gửi cho 1 người
	            Account acc = accountRepository.findById(dto.getAccountId())
	                    .orElseThrow(() -> new RuntimeException("Account not found"));
	            receivers.add(acc);
	        } else {
	            // gửi theo role
	            receivers = roleAccountRepository
	                    .findAccountsByRoleName(dto.getReceiverType().name());
	        }

	        // 2️⃣ Tạo notification cho từng account
	        for (Account acc : receivers) {

	            Notification n = new Notification();
	            n.setAccount(acc);

	            switch (dto.getTypeNotification()) {
	                case PRODUCT -> n.setProduct(
	                        productRepository.findById(dto.getProductId())
	                                .orElseThrow(() -> new RuntimeException("Product not found"))
	                );
	                case CATEGORY -> n.setCategory(
	                        categoryRepository.findById(dto.getCategoryId())
	                                .orElseThrow(() -> new RuntimeException("Category not found"))
	                );
	                case ORDER -> n.setOrder(
	                        orderRepository.findById(dto.getOrderId())
	                                .orElseThrow(() -> new RuntimeException("Order not found"))
	                );
	                case PROMOTION -> n.setPromotion(
	                        promotionRepository.findById(dto.getPromotionId())
	                                .orElseThrow(() -> new RuntimeException("Promotion not found"))
	                );
	            }

	            n.setReceiverType(dto.getReceiverType());
	            n.setTypeNotification(dto.getTypeNotification());
	            n.setTitleNotification(dto.getTitleNotification());
	            n.setMessageNotification(dto.getMessageNotification());
	            n.setIsRead(false);
	            n.setCreatedAt(new Date());

	            notificationRepository.save(n);
	        }

	        return true;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	@Override
	public List<NotificationDTO> findByAccount(Integer accountId) {
		return notificationRepository.findByAccount_IdOrderByCreatedAtDesc(accountId).stream()
				.map(n -> modelMapper.map(n, NotificationDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<NotificationDTO> findUnreadByAccount(Integer accountId) {
		return notificationRepository.findByAccount_IdAndIsReadFalseOrderByCreatedAtDesc(accountId).stream()
				.map(n -> modelMapper.map(n, NotificationDTO.class)).collect(Collectors.toList());
	}

	@Override
	public boolean markAsRead(Integer notificationId) {
		Notification notification = notificationRepository.findById(notificationId).orElse(null);
		if (notification == null)
			return false;

		notification.setIsRead(true);
		notificationRepository.save(notification);
		return true;
	}

	@Override
	public boolean markAllAsRead(Integer accountId) {
		List<Notification> notifications = notificationRepository
				.findByAccount_IdAndIsReadFalseOrderByCreatedAtDesc(accountId);

		if (notifications.isEmpty())
			return true;

		notifications.forEach(n -> n.setIsRead(true));
		notificationRepository.saveAll(notifications);
		return true;
	}

	@Override
	public NotificationDTO findById(Integer id) {
		Notification notification = notificationRepository.findById(id).get();
		return modelMapper.map(notification, NotificationDTO.class);
	}
}
