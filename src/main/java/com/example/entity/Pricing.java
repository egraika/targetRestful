package com.example.entity;

import org.springframework.data.annotation.Id;

public class Pricing {
	
	@Id private String id;
	private int productID;
	private String name;
	private CurrentPricing current_price;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public CurrentPricing getCurrent_Price() {
		return current_price;
	}

	public void setCurrent_Price(CurrentPricing current_price) {
		this.current_price = current_price;
	}
	
	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}
}
