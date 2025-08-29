package com.stock.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Portfolio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	private double totalInvested;
	
	private double totalCurrentValue;
	
	private String status;
	
	private double statusValue;
	
	private String statusPercentage;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
	
    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    @JsonManagedReference
    private java.util.List<Stock> stocks;

	

	public Portfolio(String name, double totalInvested, double totalCurrentValue, String status, double statusValue,
			String statusPercentage, User user, List<Stock> stocks) {
		super();
		this.name = name;
		this.totalInvested = totalInvested;
		this.totalCurrentValue = totalCurrentValue;
		this.status = status;
		this.statusValue = statusValue;
		this.statusPercentage = statusPercentage;
		this.user = user;
		this.stocks = stocks;
	}

	public Portfolio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public java.util.List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(java.util.List<Stock> stocks) {
		this.stocks = stocks;
	}

	public double getTotalInvested() {
		return totalInvested;
	}

	public void setTotalInvested(double totalInvested) {
		this.totalInvested = totalInvested;
	}

	public double getTotalCurrentValue() {
		return totalCurrentValue;
	}

	public void setTotalCurrentValue(double totalCurrentValue) {
		this.totalCurrentValue = totalCurrentValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getStatusValue() {
		return statusValue;
	}

	public void setStatusValue(double statusValue) {
		this.statusValue = statusValue;
	}

	public String getStatusPercentage() {
		return statusPercentage;
	}

	public void setStatusPercentage(String statusPercentage) {
		this.statusPercentage = statusPercentage;
	}

	
    
    
    
}
