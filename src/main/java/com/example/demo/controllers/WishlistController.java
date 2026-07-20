package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

}
