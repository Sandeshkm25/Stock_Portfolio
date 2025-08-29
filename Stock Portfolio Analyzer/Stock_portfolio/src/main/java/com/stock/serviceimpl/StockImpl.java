package com.stock.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.stock.entity.Stock;
import com.stock.repo.StockRepo;
import com.stock.service.StockService;
import com.stock.util.PortfolioAutoUpdate;

@Service
public class StockImpl implements StockService{

	@Autowired
	private StockRepo repo;
	
	PortfolioAutoUpdate autoUpdate;
	

	@Override
	public Stock addStockToPortfolio( Stock stock) {
		Stock stock1=repo.save(stock);
		autoUpdate.summaryPortfolio(stock1.getPortfolio());
		return stock1;

	}

	@Override
	public List<Stock> getStocksByPortfolio(Long portfolioId) {
		return repo.getStocksByPortfolioId(portfolioId);

	}

	@Override
	public String deleteStock(Long stockId) {
		try {
		     Stock stock=getStockById(stockId);
		     if(stock!=null) {
		    	 repo.deleteById(stockId);
		    	 autoUpdate.summaryPortfolio(stock.getPortfolio());
		     }
		}
		catch (Exception e) {
			e.getMessage();
		}
		
		return "Deleted";

	}

	public Stock getStockById(Long stockId) {
		return repo.findById(stockId).orElseThrow(()->new RuntimeException("Not found"));
	}
	
	
	@Override
	public void updateCurrentPrice(String ticker) {

		
	}

}
