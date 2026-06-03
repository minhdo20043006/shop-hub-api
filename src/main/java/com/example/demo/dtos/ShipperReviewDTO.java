package com.example.demo.dtos;

import java.util.Date;

public class ShipperReviewDTO {
	private Integer id;
	private Integer accountId;
	private String accountName;
	private Integer shipperProfileId;
	private int rating;
	private String commentReview;
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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Integer getShipperProfileId() {
		return shipperProfileId;
	}

	public void setShipperProfileId(Integer shipperProfileId) {
		this.shipperProfileId = shipperProfileId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getCommentReview() {
		return commentReview;
	}

	public void setCommentReview(String commentReview) {
		this.commentReview = commentReview;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public ShipperReviewDTO(Integer id, Integer accountId, String accountName, Integer shipperProfileId, int rating,
			String commentReview, Date createdAt) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.accountName = accountName;
		this.shipperProfileId = shipperProfileId;
		this.rating = rating;
		this.commentReview = commentReview;
		this.createdAt = createdAt;
	}

	public ShipperReviewDTO() {
		super();
	}

}
