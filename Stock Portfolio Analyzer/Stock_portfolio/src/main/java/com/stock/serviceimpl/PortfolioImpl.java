package com.stock.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stock.entity.Portfolio;
import com.stock.repo.PortfolioRepo;
import com.stock.service.PortfolioService;

@Service
public class PortfolioImpl implements PortfolioService{


	@Autowired
	PortfolioRepo repo;
	@Override
	public Portfolio createPortfolio(Portfolio p) {
		return repo.save(p);
		
	}

	@Override
	public List<Portfolio> getAllPortfolios() {
		return repo.findAll();
		
	}

	@Override
	public String deletePortfolio(Long id) {
		repo.deleteById(id);
		return "deleted";
		
	}

	@Override
	public Portfolio getportfolio(Long id) {
		// TODO Auto-generated method stub
		Portfolio portfolio=repo.findById(id).orElseThrow(()-> new RuntimeException("Portolio not found"));
		return portfolio;
	}

	@Override
	public Map<String, Object> getPortfolioSummary(Long id) {
		Portfolio portfolio=repo.findById(id).orElseThrow(()->new RuntimeException("portfolio not found"));
		
		
		Map<String, Object> summary=new HashMap<>();
		summary.put("Portfoio name", portfolio.getName());
		summary.put("Total Invested", portfolio.getTotalInvested());
	    summary.put("Current Value", portfolio.getTotalCurrentValue());
	    summary.put("result", portfolio.getStatusValue());
	    summary.put("status", portfolio.getStatus());
	   
		return summary;
	}

	
	@Override
	public Portfolio summaryPortfolioId(Long id) {
		Portfolio portfolio=repo.findById(id).orElseThrow(()->new RuntimeException("Portfolio with ID: "+id));
		return portfolio;
	}

	
}
