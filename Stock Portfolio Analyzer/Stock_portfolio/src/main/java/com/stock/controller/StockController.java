package com.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stock.entity.Stock;
import com.stock.service.DashboardService;
import com.stock.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {

	@Autowired
	DashboardService dashboardService;
	
	@Autowired
	StockService service;

	@PostMapping("/add")
	public ResponseEntity<?> addStockToPortfolio(@RequestBody Stock stock) {
		try {
			return ResponseEntity.ok(service.addStockToPortfolio(stock));
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{portfolioId}")
	public ResponseEntity<?> getStocksByPortfolio(@PathVariable Long portfolioId) {
		try {
			return ResponseEntity.ok(service.getStocksByPortfolio(portfolioId));
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?>  deleteStock(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(service.deleteStock(id));
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}/dashboard")
	public Page<Stock> dashboard(@RequestParam Long portfolioId,
			@RequestParam int page,
			@RequestParam int size,
			@RequestParam(defaultValue = "all") String filter
			){

		 return dashboardService.getDashboard(portfolioId, page, size, filter);
	}
}
