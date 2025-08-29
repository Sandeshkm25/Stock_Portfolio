package com.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.entity.Portfolio;
import com.stock.service.PortfolioService;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

	@Autowired
	PortfolioService service;
	
	@PostMapping("/create")
	public ResponseEntity<?> createPortfolio(@RequestBody Portfolio portfolio) {
		try {
			return ResponseEntity.ok(service.createPortfolio(portfolio));
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllPortfolio(){
		try {
			return ResponseEntity.ok(service.getAllPortfolios());
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePortfolio(@PathVariable Long id) {
		try {
			
			return (ResponseEntity<?>) ResponseEntity.ok(service.deletePortfolio(id));
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		try {
			return ResponseEntity.ok(service.getportfolio(id));
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@GetMapping("/{id}/summary")
	public ResponseEntity<?> getSummary(@PathVariable Long id){
		try {
			return ResponseEntity.ok(service.getPortfolioSummary(id));
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@GetMapping("/{id}/dashboard")
	public ResponseEntity<?> getdashboard(@PathVariable Long id){
		try {
			return ResponseEntity.ok(service.summaryPortfolioId(id));
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
