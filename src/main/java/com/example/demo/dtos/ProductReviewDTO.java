package com.example.demo.dtos;

import java.util.Date;

import com.example.demo.entities.Account;
import com.example.demo.entities.Product;

public class ProductReviewDTO {
	private Integer id;
	private Integer accountId;
	private String accountName;
	private Integer productId;
	private String productName;
	private int rating;
	private String commentReview;
	private Date createdAt;
	

	public ProductReviewDTO(Integer id, Integer accountId, String accountName, Integer productId, String productName,
			int rating, String commentReview, Date createdAt) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.accountName = accountName;
		this.productId = productId;
		this.productName = productName;
		this.rating = rating;
		this.commentReview = commentReview;
		this.createdAt = createdAt;
		
	}

	public ProductReviewDTO() {
		super();
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

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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


}
