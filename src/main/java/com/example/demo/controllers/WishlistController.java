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

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
	
	@Autowired
	private WishlistService wishlistService;
	
	@PostMapping("/add")
	public WishlistResponse addToWishlist(@RequestBody WishlistRequest request )
	{
		return wishlistService.addToWishlist(request);
	}
	@GetMapping("/myWishlist")
	public List<WishlistResponse> mywishlist()
	{
		return wishlistService.myWishlist();
	}
	@DeleteMapping("/delete/{wishlistId}")
	public String removeFromWishlist(@PathVariable Integer wishlistId)
	{
		return wishlistService.removeFromWishlist(wishlistId);
	}
	@PostMapping("/move-to-cart/{wishlistId}")
	public String moveToCart(@PathVariable Integer wishlistId)
	{
		return wishlistService.moveToCart(wishlistId);
	}
	

}
