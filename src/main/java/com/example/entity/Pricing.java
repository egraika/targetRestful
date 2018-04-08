package com.example.entity;

import org.springframework.data.annotation.Id;

public class Pricing {

	@Id private String id;

	private String price;
	private String currency;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
