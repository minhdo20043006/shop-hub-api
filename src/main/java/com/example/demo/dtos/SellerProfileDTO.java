package com.example.demo.dtos;

import java.util.Date;

import com.example.demo.entities.Account;
import com.example.demo.enums.ApprovedStatus;

public class SellerProfileDTO {
	private Integer id;
	private Integer accountId;
	private String accountName;
	private String storeName;
	private String taxCode;
	private String businessLicenseNumber;
	private String storeAddress;
	private String logo;
	private String description;
	private ApprovedStatus approvedStatus;
	private Date createdAt;
	private Date updatedAt;
	private int reviewCount;
	private float avgRating;

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public float getAvgRating() {
		return avgRating;
	}

	public SellerProfileDTO(Integer id, Integer accountId, String accountName, String storeName, String taxCode,
			String businessLicenseNumber, String storeAddress, String logo, String description, ApprovedStatus approvedStatus,
			Date createdAt, Date updatedAt, int reviewCount, float avgRating) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.accountName = accountName;
		this.storeName = storeName;
		this.taxCode = taxCode;
		this.businessLicenseNumber = businessLicenseNumber;
		this.storeAddress = storeAddress;
		this.logo = logo;
		this.description = description;
		this.approvedStatus = approvedStatus;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.reviewCount = reviewCount;
		this.avgRating = avgRating;
	}

	public void setAvgRating(float avgRating) {
		this.avgRating = avgRating;
	}

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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getBusinessLicenseNumber() {
		return businessLicenseNumber;
	}

	public void setBusinessLicenseNumber(String businessLicenseNumber) {
		this.businessLicenseNumber = businessLicenseNumber;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ApprovedStatus getApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(ApprovedStatus approvedStatus) {
		this.approvedStatus = approvedStatus;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public SellerProfileDTO(Integer id, Integer accountId, String accountName, String storeName, String taxCode,
			String businessLicenseNumber, String storeAddress, String logo, String description, ApprovedStatus approvedStatus,
			Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.accountName = accountName;
		this.storeName = storeName;
		this.taxCode = taxCode;
		this.businessLicenseNumber = businessLicenseNumber;
		this.storeAddress = storeAddress;
		this.logo = logo;
		this.description = description;
		this.approvedStatus = approvedStatus;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public SellerProfileDTO() {
		super();
	}

}
