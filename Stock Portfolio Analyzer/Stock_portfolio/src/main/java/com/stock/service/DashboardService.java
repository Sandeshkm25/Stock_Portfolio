package com.stock.service;

import org.springframework.data.domain.Page;

import com.stock.entity.Stock;

public interface DashboardService {

	Page<Stock> getDashboard(Long portfolioId, int page, int size, String filterType);
}
