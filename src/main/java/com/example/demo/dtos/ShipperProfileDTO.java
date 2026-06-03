package com.example.demo.dtos;

import java.math.BigInteger;
import java.util.Date;

import com.example.demo.entities.Account;

public class ShipperProfileDTO {
	private Integer id;
	private Integer accountId;
	private String accountName;
	private String vehicleType;
	private String licensePlate;
	private String drivingLicenseNumber;
	private String status;
	private boolean available;
	private Date createdAt;
	private Date updatedAt;
	private int totalDeliveries;
	private BigInteger currentLatitude;
	private BigInteger currentLongitude;
	private int reviewCount;
	private float avgRating;

	public ShipperProfileDTO(Integer id, Integer accountId, String accountName, String vehicleType, String licensePlate,
			String drivingLicenseNumber, String status, boolean available, Date createdAt, Date updatedAt,
			int totalDeliveries, BigInteger currentLatitude, BigInteger currentLongitude, int reviewCount,
			float avgRating) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.accountName = accountName;
		this.vehicleType = vehicleType;
		this.licensePlate = licensePlate;
		this.drivingLicenseNumber = drivingLicenseNumber;
		this.status = status;
		this.available = available;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.totalDeliveries = totalDeliveries;
		this.currentLatitude = currentLatitude;
		this.currentLongitude = currentLongitude;
		this.reviewCount = reviewCount;
		this.avgRating = avgRating;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public float getAvgRating() {
		return avgRating;
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

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getDrivingLicenseNumber() {
		return drivingLicenseNumber;
	}

	public void setDrivingLicenseNumber(String drivingLicenseNumber) {
		this.drivingLicenseNumber = drivingLicenseNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
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

	public int getTotalDeliveries() {
		return totalDeliveries;
	}

	public void setTotalDeliveries(int totalDeliveries) {
		this.totalDeliveries = totalDeliveries;
	}

	public BigInteger getCurrentLatitude() {
		return currentLatitude;
	}

	public void setCurrentLatitude(BigInteger currentLatitude) {
		this.currentLatitude = currentLatitude;
	}

	public BigInteger getCurrentLongitude() {
		return currentLongitude;
	}

	public void setCurrentLongitude(BigInteger currentLongitude) {
		this.currentLongitude = currentLongitude;
	}

	public ShipperProfileDTO(Integer id, Integer accountId, String accountName, String vehicleType, String licensePlate,
			String drivingLicenseNumber, String status, boolean available, Date createdAt, Date updatedAt,
			int totalDeliveries, BigInteger currentLatitude, BigInteger currentLongitude) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.accountName = accountName;
		this.vehicleType = vehicleType;
		this.licensePlate = licensePlate;
		this.drivingLicenseNumber = drivingLicenseNumber;
		this.status = status;
		this.available = available;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.totalDeliveries = totalDeliveries;
		this.currentLatitude = currentLatitude;
		this.currentLongitude = currentLongitude;
	}

	public ShipperProfileDTO() {
		super();
	}

}
