package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.OrderResponse;
import com.example.demo.DTO.UpdateOrderStatusRequest;
import com.example.demo.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderservice;
	
	@GetMapping("/myorders")
	public List<OrderResponse> myorders()
	{
		return orderservice.myOrders();
	}
	@GetMapping
	public List<OrderResponse> getAllOrders()
	{
		return orderservice.getAllOrders();
	}
	@PutMapping("{orderId}/status")
	public OrderResponse updateOrderStatus(@PathVariable Integer orderId,@RequestBody UpdateOrderStatusRequest request)
	{
		return orderservice.updateOrderStatus(orderId, request);
	}
}
