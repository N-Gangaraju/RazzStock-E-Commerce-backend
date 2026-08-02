package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.RecentOrderResponse;
import com.example.demo.DTO.TopCustomerResponse;
import com.example.demo.DTO.TopSellingProductsResponse;
import com.example.demo.Entites.DashboardResponse;
import com.example.demo.Entites.Order;
import com.example.demo.controllers.MonthlyRevenueResponse;
import com.example.demo.repos.OrderRepo;
import com.example.demo.repos.ProductRepo;
import com.example.demo.repos.UserRepo;

@Service
public class DashboardService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private OrderRepo orderRepo;
	
	public DashboardResponse getDashboard()
	{
		DashboardResponse response = new DashboardResponse();
		response.setTotalUsers(userRepo.count());
		response.setTotalProducts(productRepo.count());
		response.setTotalOrders(orderRepo.count());
		response.setTotalRevenue(orderRepo.getTotalRevenue());
		response.setLowStockProducts(productRepo.countLowStockProducts());
		return response;
	}
	public List<TopSellingProductsResponse> getTopSellingProducts() 
	{

	    List<Object[]> result = orderRepo.getTopSellingProducts();

	    List<TopSellingProductsResponse> response = new ArrayList<>();

	    for (Object[] row : result) 
	    {

	        TopSellingProductsResponse dto = new TopSellingProductsResponse();

	        dto.setProductName((String) row[0]);

	        dto.setQuantitySold(((Number) row[1]).longValue());

	        response.add(dto);
	    }

	    return response;
	}
	public List<RecentOrderResponse> getRecentOrders() {

	    List<Order> orders = orderRepo.findTop10ByOrderByOrderedAtDesc();

	    List<RecentOrderResponse> response = new ArrayList<>();

	    for (Order order : orders) {

	        RecentOrderResponse dto = new RecentOrderResponse();

	        dto.setOrderId(order.getId());
	        dto.setCustomerName(order.getUser().getUsername());
	        dto.setProductName(order.getProduct().getName());
	        dto.setQuantity(order.getQuantity());
	        dto.setAmount(order.getAmount());
	        dto.setStatus(order.getStatus().name());
	        dto.setOrderedAt(order.getOrderedAt());

	        response.add(dto);
	    }

	    return response;
	}
	public List<MonthlyRevenueResponse> getMonthlyRevenue() {

	    List<Object[]> result = orderRepo.getMonthlyRevenue();

	    List<MonthlyRevenueResponse> response = new ArrayList<>();

	    for (Object[] row : result) {

	        MonthlyRevenueResponse dto = new MonthlyRevenueResponse();

	        dto.setMonth((String) row[0]);

	        dto.setRevenue(((Number) row[1]).doubleValue());

	        response.add(dto);
	    }

	    return response;
	}
	public List<TopCustomerResponse> getTopCustomers() {

	    List<Object[]> result = orderRepo.getTopCustomers();

	    List<TopCustomerResponse> response = new ArrayList<>();

	    for (Object[] row : result) {

	        TopCustomerResponse dto = new TopCustomerResponse();

	        dto.setCustomerName((String) row[0]);
	        dto.setTotalOrders(((Number) row[1]).longValue());
	        dto.setTotalSpent(((Number) row[2]).doubleValue());

	        response.add(dto);
	    }

	    return response;
	}
	
}
