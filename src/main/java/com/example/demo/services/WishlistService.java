package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.WishlistRequest;
import com.example.demo.DTO.WishlistResponse;
import com.example.demo.Entites.Product;
import com.example.demo.Entites.User;
import com.example.demo.Entites.Wishlist;
import com.example.demo.repos.ProductRepo;
import com.example.demo.repos.UserRepo;
import com.example.demo.repos.WishlistRepo;

@Service
public class WishlistService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private WishlistRepo wishlistRepo;
	@Autowired
	private ProductRepo  productRepo;
	
	public WishlistResponse addToWishlist(WishlistRequest request)
	{
		// 1. Get logged-in username
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();

	    // 2. Get User object
	    User user = userRepo.findByUsername(username)
	            .orElseThrow(() -> new RuntimeException("User Not Found"));

	    // 3. Get Product object
	    Product product = productRepo.findById(request.getProductId())
	            .orElseThrow(() -> new RuntimeException("Product Not Found"));


	    // 4. Check if product already exists in wishlist
	    Optional<Wishlist> existingWishlist = wishlistRepo.findByUserAndProduct(user, product);

	    if(existingWishlist.isPresent())
	    {
	        throw new RuntimeException("Product already exists in Wishlist");
	    }

	    // 5. Create Wishlist object
	    Wishlist wishlist = new Wishlist();

	    wishlist.setUser(user);
	    wishlist.setProduct(product);
	    wishlist.setAddedAt(LocalDateTime.now());

	    // 6. Save into database
	    Wishlist savedWishlist = wishlistRepo.save(wishlist);

	    // 7. Prepare Response
	    WishlistResponse response = new WishlistResponse();

	    response.setWishlistid(savedWishlist.getId());
	    response.setUsername(savedWishlist.getUser().getUsername());
	    response.setProductName(savedWishlist.getProduct().getName());
	    response.setPrice(savedWishlist.getProduct().getPrice());

	    return response;
	    
	}
	
}
