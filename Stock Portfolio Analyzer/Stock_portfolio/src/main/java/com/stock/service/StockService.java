package com.stock.service;

import java.util.List;


import com.stock.entity.Stock;

public interface StockService {

	Stock addStockToPortfolio(Stock stock);

	List<Stock> getStocksByPortfolio(Long portfolioId);

	String deleteStock(Long stockId);

	void updateCurrentPrice(String Ticker);

	Stock getStockById(Long stockId);

}
