package com.stock.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock.entity.Portfolio;

@Repository
public interface PortfolioRepo extends JpaRepository<Portfolio, Long>{

}
