package com.example.demo.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.enums.DiscountTypePromotion;
import com.example.demo.enums.PromotionStatus;

public class PromotionDTO {

	private Integer id;
	private String namePromotion;
	private String description;
	private DiscountTypePromotion discountType;
	private int discountValue;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	private PromotionStatus statusPromotion;
	private int maxDiscount;
	private int minOrderValue;
	private int quantityPromotion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNamePromotion() {
		return namePromotion;
	}

	public void setNamePromotion(String namePromotion) {
		this.namePromotion = namePromotion;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DiscountTypePromotion getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountTypePromotion discountType) {
		this.discountType = discountType;
	}

	public int getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(int discountValue) {
		this.discountValue = discountValue;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public PromotionStatus getStatusPromotion() {
		return statusPromotion;
	}

	public void setStatusPromotion(PromotionStatus statusPromotion) {
		this.statusPromotion = statusPromotion;
	}

	public int getMaxDiscount() {
		return maxDiscount;
	}

	public void setMaxDiscount(int maxDiscount) {
		this.maxDiscount = maxDiscount;
	}

	public int getMinOrderValue() {
		return minOrderValue;
	}

	public void setMinOrderValue(int minOrderValue) {
		this.minOrderValue = minOrderValue;
	}

	public int getQuantityPromotion() {
		return quantityPromotion;
	}

	public void setQuantityPromotion(int quantityPromotion) {
		this.quantityPromotion = quantityPromotion;
	}

	public PromotionDTO(Integer id, String namePromotion, String description, DiscountTypePromotion discountType,
			int discountValue, Date startDate, Date endDate, PromotionStatus statusPromotion, int maxDiscount,
			int minOrderValue, int quantityPromotion) {
		super();
		this.id = id;
		this.namePromotion = namePromotion;
		this.description = description;
		this.discountType = discountType;
		this.discountValue = discountValue;
		this.startDate = startDate;
		this.endDate = endDate;
		this.statusPromotion = statusPromotion;
		this.maxDiscount = maxDiscount;
		this.minOrderValue = minOrderValue;
		this.quantityPromotion = quantityPromotion;
	}

	public PromotionDTO() {
		super();
	}

}
