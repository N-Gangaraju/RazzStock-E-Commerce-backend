package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.OrderResponse;
import com.example.demo.DTO.UpdateOrderStatusRequest;
import com.example.demo.Entites.Order;
import com.example.demo.Entites.User;
import com.example.demo.repos.OrderRepo;
import com.example.demo.repos.UserRepo;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orderrepo;
	@Autowired
	private UserRepo userrepo;
	
	public List<OrderResponse> myOrders()
	{
	    Authentication authentication =
	            SecurityContextHolder.getContext().getAuthentication();

	    String username = authentication.getName();

	    User user = userrepo.findByUsername(username)
	            .orElseThrow(() -> new RuntimeException("User Not Found"));

	    List<Order> orders = orderrepo.findByUser(user);

	    List<OrderResponse> savedResponse = new ArrayList<>();

	    // converting the orders to response
	    for(Order order  : orders)
	    {
	    	OrderResponse response = new OrderResponse();
	    	response.setOrderId(order.getId());
	    	response.setUsername(order.getUser().getUsername());
	    	response.setProductName(order.getProduct().getName());
	    	response.setQuantity(order.getQuantity());
	    	response.setPrice(order.getProduct().getPrice());
	    	response.setAmount(order.getProduct().getPrice()* order.getQuantity());
	    	response.setStatus(order.getStatus().name());
	    	response.setOrderedAt(order.getOrderedAt().toString());
	    	savedResponse.add(response);
	    }

	    return savedResponse;
	}
	public List<OrderResponse> getAllOrders()
	{
		List<Order> orders = orderrepo.findAll();
		List<OrderResponse>responses = new ArrayList<>();
		
		for(Order order : orders)
		{
		    OrderResponse response = new OrderResponse();

		    response.setOrderId(order.getId());
		    response.setUsername(order.getUser().getUsername());
		    response.setProductName(order.getProduct().getName());

		    response.setQuantity(order.getQuantity());

		    response.setPrice(order.getPrice());
		    response.setAmount(order.getAmount());

		    response.setStatus(order.getStatus().name());

		    response.setOrderedAt(order.getOrderedAt().toString());

		    responses.add(response);
		}
		return responses;	
	}
	public OrderResponse updateOrderStatus(Integer orderId,UpdateOrderStatusRequest request)
	{
		Order order = orderrepo.findById(orderId)
				.orElseThrow(()->new RuntimeException("Order Not Found"));
		
		order.setStatus(request.getStatus());
		
		Order updatedOrder = orderrepo.save(order);
		
		OrderResponse response = new OrderResponse();

		response.setOrderId(updatedOrder.getId());
		response.setUsername(updatedOrder.getUser().getUsername());
		response.setProductName(updatedOrder.getProduct().getName());

		response.setQuantity(updatedOrder.getQuantity());
		response.setPrice(updatedOrder.getPrice());
		response.setAmount(updatedOrder.getAmount());

		response.setStatus(updatedOrder.getStatus().name());

		response.setOrderedAt(updatedOrder.getOrderedAt().toString());

		return response;
		
	}
	
}
