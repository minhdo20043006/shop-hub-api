package com.example.demo.dtos;

import java.util.Date;

import com.example.demo.enums.NotificationType;
import com.example.demo.enums.ReceiverType;

public class NotificationDTO {

	private Integer id;
	private Integer accountId;
	private Integer categoryId;
	private Integer orderId;
	private Integer productId;
	private Integer promotionId;
	private ReceiverType receiverType;
	private NotificationType typeNotification;
	private String titleNotification;
	private String messageNotification;
	private boolean isRead;
	private Date createdAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}

	public ReceiverType getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(ReceiverType receiverType) {
		this.receiverType = receiverType;
	}

	public NotificationType getTypeNotification() {
		return typeNotification;
	}

	public void setTypeNotification(NotificationType typeNotification) {
		this.typeNotification = typeNotification;
	}

	public String getTitleNotification() {
		return titleNotification;
	}

	public void setTitleNotification(String titleNotification) {
		this.titleNotification = titleNotification;
	}

	public String getMessageNotification() {
		return messageNotification;
	}

	public void setMessageNotification(String messageNotification) {
		this.messageNotification = messageNotification;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public NotificationDTO(Integer id, Integer accountId, Integer categoryId, Integer orderId, Integer productId,
			Integer promotionId, ReceiverType receiverType, NotificationType typeNotification, String titleNotification,
			String messageNotification, boolean isRead, Date createdAt) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.categoryId = categoryId;
		this.orderId = orderId;
		this.productId = productId;
		this.promotionId = promotionId;
		this.receiverType = receiverType;
		this.typeNotification = typeNotification;
		this.titleNotification = titleNotification;
		this.messageNotification = messageNotification;
		this.isRead = isRead;
		this.createdAt = createdAt;
	}

	public NotificationDTO() {
		super();
	}

}
