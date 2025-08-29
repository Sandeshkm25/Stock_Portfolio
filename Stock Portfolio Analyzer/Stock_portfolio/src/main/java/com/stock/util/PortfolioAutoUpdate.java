package com.stock.util;

import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.entity.Portfolio;
import com.stock.entity.Stock;
import com.stock.repo.PortfolioRepo;

@Service
public class PortfolioAutoUpdate {

	private static final Logger logger = LoggerFactory.getLogger(PortfolioAutoUpdate.class);
	
	@Autowired
	PortfolioRepo repo;
	
	public void summaryPortfolio(Portfolio portfolio) {
        if (portfolio == null || portfolio.getStocks() == null) {
            logger.warn("Portfolio or stocks list is null.");
            return;
        }

        double currentPrice = 0;
        double totalInvested = 0;

        for (Stock stock : portfolio.getStocks()) {
            currentPrice += stock.getCurrent_price() * stock.getQuantity();
            totalInvested += stock.getBuy_price() * stock.getQuantity();
        }

        double netPL = currentPrice - totalInvested;
        String status = netPL >= 0 ? "PROFIT" : "LOSS";
        double percentage = totalInvested > 0 ? (netPL / totalInvested) * 100 : 0;

        DecimalFormat df = new DecimalFormat("0.00");

        portfolio.setTotalCurrentValue(currentPrice);
        portfolio.setTotalInvested(totalInvested);
        portfolio.setStatus(status);
        portfolio.setStatusValue(netPL);
        portfolio.setStatusPercentage(df.format(percentage) + "%");

        try {
            repo.save(portfolio);
        } catch (Exception e) {
            logger.error("Error saving portfolio summary: {}", e.getMessage(), e);
        }
    }
	
//	public void summaryPortfolio(Portfolio portfolio) {
//		
//		double currentPrice=0;
//		double totalInvested=0;
//		double netPL=0;
//		double percentage;
//		
//		for(Stock stock:portfolio.getStocks()) {
//			currentPrice+=stock.getCurrent_price()*stock.getQuantity();
//			totalInvested+=stock.getBuy_price()*stock.getQuantity();
//		}
//		
//		portfolio.setTotalCurrentValue(currentPrice);
//		portfolio.setTotalInvested(totalInvested);
//		
//		if(currentPrice>=totalInvested) {
//			netPL=currentPrice-totalInvested;
//			portfolio.setStatus("PROFIT");
//			
//		}		
//		else {
//			portfolio.setStatus("LOSS");
//		}
//		percentage=((currentPrice-totalInvested)/totalInvested)*100;
//		portfolio.setStatusValue(netPL);
//		
//		portfolio.setStatusPercentage(percentage+"%");
//		
//		repo.save(portfolio);
//	    return ;
//	}
}
