package com.stock.serviceimpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.stock.entity.Stock;
import com.stock.repo.StockRepo;

@Service
public class DashboardImpl implements com.stock.service.DashboardService{

	StockRepo stockRepository;
	
	@Override
	public Page<Stock> getDashboard(Long portfolioId, int page, int size, String filterType) {
		 PageRequest pageRequest = PageRequest.of(page, size);

	        switch (filterType.toLowerCase()) {
	            case "profit":
	                return stockRepository.findByPortfolioIdAndCurrent_PriceGreaterThanBuy_Price(portfolioId, pageRequest);
	            case "loss":
	                return stockRepository.findByPortfolioIdAndCurrent_PriceLessThanBuy_Price(portfolioId, pageRequest);
	            default:
	                return stockRepository.findByPortfolioId(portfolioId, pageRequest);
	        }
	}

}
