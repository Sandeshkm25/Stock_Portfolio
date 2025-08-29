package com.stock.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stock.entity.Stock;

@Repository
public interface StockRepo extends JpaRepository<Stock, Long>{

	@Query("SELECT s FROM Stock s WHERE s.portfolio.id = :portfolioId")
	List<Stock> getStocksByPortfolioId(@Param("portfolioId") Long portfolioId);

	Optional<Stock> findByTicker(String ticker);
	
	Page<Stock> findByPortfolioId(Long portfolioId, Pageable pageable);

	@Query("SELECT s FROM Stock s where s.portfolio.id = :portfolioId AND s.current_price>s.buy_price")
    Page<Stock> findByPortfolioIdAndCurrent_PriceGreaterThanBuy_Price(@Param("portfolioId") Long portfolioId, Pageable pageable);

	@Query("SELECT s FROM Stock s where s.portfolio.id = :portfolioId AND s.current_price>s.buy_price")
    Page<Stock> findByPortfolioIdAndCurrent_PriceLessThanBuy_Price(@Param("portfolioId") Long portfolioId, Pageable pageable);
   
}
