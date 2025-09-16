📈 Stock Portfolio Analyzer

A Spring Boot application that helps users manage their stock portfolio, track real-time prices, monitor profits & losses, and visualize trends with analytics.
🚀 Features

Portfolio Management

Create and manage portfolios

Add/remove stocks with ticker, buy price, and quantity

Real-Time Stock Price Updates

Automatic background update of stock prices using Twelve Data API

Portfolio Summary

Current Value, Invested Value, Net Profit/Loss, Status (Profit/Loss)

Filtering & Pagination

Fetch stocks efficiently with pagination support

Analytics & Trends

Portfolio value trend for charts

Top gainer/loser stocks in portfolio

🏗️ Tech Stack

Backend: Spring Boot (Java)

Database: MySQL

Scheduler: Spring @Scheduled (for auto price updates)

API Integration: Twelve Data API (for stock prices)
Notifications (Optional)

Alerts when a stock crosses profit/loss threshold

src/main/java/com/example/portfolio
│
├── controller
│   └── PortfolioController.java
│
├── service
│   ├── PortfolioService.java
│   ├── StockService.java
│   └── AutoUpdateService.java
│
├── model
│   ├── Portfolio.java
│   └── Stock.java
│
├── repository
│   ├── PortfolioRepository.java
│   └── StockRepository.java
│
└── scheduler
    ├── PortfolioAutoUpdate.java
    └── StockAutoUpdate.java
