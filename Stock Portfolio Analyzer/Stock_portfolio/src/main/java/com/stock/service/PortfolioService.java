package com.stock.service;

import java.util.List;
import java.util.Map;

import com.stock.entity.Portfolio;

public interface PortfolioService {

	Portfolio createPortfolio(Portfolio p);
	
	List<Portfolio> getAllPortfolios();
	
	String deletePortfolio(Long id);

	Portfolio getportfolio(Long id);
	
	Map<String,Object> getPortfolioSummary(Long id);

	Portfolio summaryPortfolioId(Long id);
}
