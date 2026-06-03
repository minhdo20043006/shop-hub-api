package com.example.demo.dtos;

import com.example.demo.entities.Account;
import com.example.demo.entities.Promotion;

public class PromotionAccountDTO {
	private Integer id;
	private Integer accountId;
	private String accountName;
	private Integer promotionId;
	private String promotionName;

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

	public Integer getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public PromotionAccountDTO(Integer id, Integer accountId, String accountName, Integer promotionId,
			String promotionName) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.accountName = accountName;
		this.promotionId = promotionId;
		this.promotionName = promotionName;
	}

	public PromotionAccountDTO() {
		super();
	}

}
