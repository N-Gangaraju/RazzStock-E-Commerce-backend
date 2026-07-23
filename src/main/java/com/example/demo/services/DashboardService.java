package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.TopSellingProductsResponse;
import com.example.demo.Entites.DashboardResponse;
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
	
}
