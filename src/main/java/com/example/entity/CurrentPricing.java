package com.example.entity;

import org.springframework.data.annotation.Id;

public class CurrentPricing {
	
	private double value;
	private String currency_code;
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getCurrency_Code() {
		return currency_code;
	}

	public void setCurrency_Code(String currency_code) {
		this.currency_code = currency_code;
	}
}
