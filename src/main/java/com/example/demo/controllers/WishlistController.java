package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.WishlistRequest;
import com.example.demo.DTO.WishlistResponse;
import com.example.demo.services.WishlistService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
	
	@Autowired
	private WishlistService wishlistService;
	
	@Operation(summary = "Add  product to Wishlist")
	@PostMapping("/add")
	public WishlistResponse addToWishlist(@RequestBody WishlistRequest request )
	{
		return wishlistService.addToWishlist(request);
	}
	
	@Operation(summary = "View Wishlist")
	@GetMapping("/myWishlist")
	public List<WishlistResponse> mywishlist()
	{
		return wishlistService.myWishlist();
	}
	
	@Operation(summary = "Remove Product from wishlist")
	@DeleteMapping("/delete/{wishlistId}")
	public String removeFromWishlist(@PathVariable Integer wishlistId)
	{
		return wishlistService.removeFromWishlist(wishlistId);
	}
	@Operation(
	        summary = "Move to Cart"
	    )
	@PostMapping("/move-to-cart/{wishlistId}")
	public String moveToCart(@PathVariable Integer wishlistId)
	{
		return wishlistService.moveToCart(wishlistId);
	}
	

}
