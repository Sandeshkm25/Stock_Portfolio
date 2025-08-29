package com.stock.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.stock.dto.StockPriceResponse;
import com.stock.entity.Stock;
import com.stock.repo.StockRepo;

import jakarta.transaction.Transactional;

@Service
public class StockAutoUpdate {

	private static final Logger logger = LoggerFactory.getLogger(StockAutoUpdate.class);
	
	@Autowired
	StockRepo repo;
	
	@Autowired
	PortfolioAutoUpdate autoUpdate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Value("${stock.api.key}")
	private String apiKey;
	

    @Scheduled(fixedRate = 1800000, initialDelay = 10000) // Every 30 mins, delay 10s after app start
    @Transactional
    public void updateStock() {
        List<Stock> stocks = repo.findAll();

        for (Stock stock : stocks) {
            try {
                String url = String.format("https://api.twelvedata.com/price?symbol=%s&apikey=%s", 
                                            stock.getTicker(), apiKey);

                StockPriceResponse priceResponse = webClientBuilder.build()
                        .get()
                        .uri(url)
                        .retrieve()
                        .bodyToMono(StockPriceResponse.class)
                        .block();

                if (priceResponse != null && priceResponse.getPrice() != null) {
                    double price = Double.parseDouble(priceResponse.getPrice());
                    stock.setCurrent_price(price);
                    updateStockSummary(stock);
                    repo.save(stock); // Save after all updates
                    autoUpdate.summaryPortfolio(stock.getPortfolio());
                } else {
                    logger.warn("Price response is null for ticker: {}", stock.getTicker());
                }
            } catch (Exception e) {
                logger.error("Error updating stock {}: {}", stock.getTicker(), e.getMessage(), e);
            }
        }
    }

    private void updateStockSummary(Stock stock) {
        double profitOrLoss = (stock.getCurrent_price() - stock.getBuy_price()) * stock.getQuantity();
        String summary;

        if (profitOrLoss >= 0) {
            summary = String.format("Profit of Ticker: %s is: %.2f", stock.getTicker(), profitOrLoss);
        } else {
            summary = String.format("Loss of Ticker: %s is: %.2f", stock.getTicker(), -profitOrLoss);
        }

        stock.setSummary(summary);
    }
	
//	public Stock summary(Stock stock) {
//		if(stock.getBuy_price()<=stock.getCurrent_price()) {
//			double profit=((stock.getCurrent_price()-stock.getBuy_price())*stock.getQuantity());
//			String profitSummary="Profit of Ticker :"+stock.getTicker()+" is :"+profit;
//			stock.setSummary(profitSummary);
//			repo.save(stock);
//			
//			return stock;
//		}
//		double loss=(stock.getBuy_price()-stock.getCurrent_price())*stock.getQuantity();
//		String lossSummary="Loss of Ticker :"+stock.getTicker()+" is :"+loss;
//		stock.setSummary(lossSummary);
//		repo.save(stock);
//		return stock;
//	}
	

}
