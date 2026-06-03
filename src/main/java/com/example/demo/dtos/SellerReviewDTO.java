package com.example.demo.dtos;

import java.util.Date;



public class SellerReviewDTO {
	private Integer id;
	private Integer accountId;
	private String accountName;
	private Integer sellerProfileId;
	private String sellerProfileName;
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
	public Integer getSellerProfileId() {
		return sellerProfileId;
	}
	public void setSellerProfileId(Integer sellerProfileId) {
		this.sellerProfileId = sellerProfileId;
	}
	public String getSellerProfileName() {
		return sellerProfileName;
	}
	public void setSellerProfileName(String sellerProfileName) {
		this.sellerProfileName = sellerProfileName;
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
	public SellerReviewDTO(Integer id, Integer accountId, String accountName, Integer sellerProfileId,
			String sellerProfileName, int rating, String commentReview, Date createdAt) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.accountName = accountName;
		this.sellerProfileId = sellerProfileId;
		this.sellerProfileName = sellerProfileName;
		this.rating = rating;
		this.commentReview = commentReview;
		this.createdAt = createdAt;
	}
	public SellerReviewDTO() {
		super();
	}
	
	
	
}
