package com.example.demo.controllers;

public class MonthlyRevenueResponse {

	private String month;
	private Double revenue;
	public MonthlyRevenueResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MonthlyRevenueResponse(String month, Double revenue) {
		super();
		this.month = month;
		this.revenue = revenue;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Double getRevenue() {
		return revenue;
	}
	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}
	
	
}

