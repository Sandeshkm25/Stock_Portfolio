package com.stock.dto;

import org.springframework.stereotype.Component;

@Component
public class StockPriceResponse {

	private String price;

	public StockPriceResponse(String price) {
		super();
		this.price = price;
	}

	public StockPriceResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	
}
