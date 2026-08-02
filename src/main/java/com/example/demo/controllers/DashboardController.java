package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.RecentOrderResponse;
import com.example.demo.DTO.TopCustomerResponse;
import com.example.demo.DTO.TopSellingProductsResponse;
import com.example.demo.Entites.DashboardResponse;
import com.example.demo.services.DashboardService;

import io.swagger.v3.oas.annotations.Operation;

@RestController

@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private DashboardService dashboardService;
	
	@Operation(summary = "Get admin dashboard statistics")
	@GetMapping
	public DashboardResponse getDashboard()
	{
		return dashboardService.getDashboard();
	}
	
	@Operation(
		    summary = "Get Top Selling Products",
		    description = "Returns the products sorted by total quantity sold in descending order."
		)
	@GetMapping("/top-selling-products")
	public List<TopSellingProductsResponse>getTopSelling()
	{
		return dashboardService.getTopSellingProducts();
	}
	
	@Operation(
	        summary = "Get Recent Orders",
	        description = "Returns the latest 10 orders placed in the system."
	)
	@GetMapping("/recent-orders")
	public List<RecentOrderResponse> getRecentOrders() {
	    return dashboardService.getRecentOrders();
	}
	
	@Operation(
	        summary = "Get Monthly Revenue",
	        description = "Returns the total revenue generated for each month."
	)
	@GetMapping("/monthly-revenue")
	public List<MonthlyRevenueResponse> getMonthlyRevenue()
	{
		return dashboardService.getMonthlyRevenue();
	}
	
	@Operation(
	        summary = "Get Top Customers",
	        description = "Returns customers ordered by total spending."
	)
	@GetMapping("/top-customers")
	public List<TopCustomerResponse> getTopCustomers() {
	    return dashboardService.getTopCustomers();
	}

}
