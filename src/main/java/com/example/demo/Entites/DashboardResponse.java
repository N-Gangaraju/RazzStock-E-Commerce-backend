package com.example.demo.Entites;

public class DashboardResponse {

	private long totalUsers;
	private long totalProducts;
	private long totalOrders;
	private Double totalRevenue;
	private long lowStockProducts;
	public DashboardResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DashboardResponse(long totalUsers, long totalProducts, long totalOrders, Double totalRevenue,
			long lowStockProducts) {
		super();
		this.totalUsers = totalUsers;
		this.totalProducts = totalProducts;
		this.totalOrders = totalOrders;
		this.totalRevenue = totalRevenue;
		this.lowStockProducts = lowStockProducts;
	}
	public long getTotalUsers() {
		return totalUsers;
	}
	public void setTotalUsers(long totalUsers) {
		this.totalUsers = totalUsers;
	}
	public long getTotalProducts() {
		return totalProducts;
	}
	public void setTotalProducts(long totalProducts) {
		this.totalProducts = totalProducts;
	}
	public long getTotalOrders() {
		return totalOrders;
	}
	public void setTotalOrders(long totalOrders) {
		this.totalOrders = totalOrders;
	}
	public Double getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(Double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public long getLowStockProducts() {
		return lowStockProducts;
	}
	public void setLowStockProducts(long lowStockProducts) {
		this.lowStockProducts = lowStockProducts;
	}
	@Override
	public String toString() {
		return "DashboardResponse [totalUsers=" + totalUsers + ", totalProducts=" + totalProducts + ", totalOrders="
				+ totalOrders + ", totalRevenue=" + totalRevenue + ", lowStockProducts=" + lowStockProducts + "]";
	}
	
	
}
