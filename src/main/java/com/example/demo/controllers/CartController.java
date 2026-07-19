package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.CartRequest;
import com.example.demo.DTO.CartResponse;
import com.example.demo.DTO.UpdateCartRequest;
import com.example.demo.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartController 
{
	@Autowired
	private CartService cartservice;
	
	@PostMapping("/add")
	public CartResponse addToCart(@RequestBody CartRequest request)
	{
		return cartservice.addToCart(request);
	}
	@GetMapping("/mycart")
	public List<CartResponse>mycart()
	{
		return cartservice.myCart();
	}
	@PutMapping("/update/{cartId}")
	public CartResponse updateCart(@PathVariable Integer cartId,@RequestBody UpdateCartRequest request)
	{
		return cartservice.updateCart(cartId, request);
	}
	@DeleteMapping("/delete/{cartId}")
	public String deleteCart(@PathVariable Integer cartId)
	{
		return cartservice.deleteCart(cartId);
	}
	@PostMapping("/checkout")
	public String checkout()
	{
		return cartservice.checkout();
	}
}
