package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.WishlistRequest;
import com.example.demo.DTO.WishlistResponse;
import com.example.demo.Entites.Cart;
import com.example.demo.Entites.Product;
import com.example.demo.Entites.User;
import com.example.demo.Entites.Wishlist;
import com.example.demo.repos.CartRepo;
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
	@Autowired
	private CartRepo cartRepo;
	
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
	public List<WishlistResponse>myWishlist()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User user = userRepo.findByUsername(username)
		        .orElseThrow(() -> new RuntimeException("User Not Found"));
		
		List<Wishlist>wishlists = wishlistRepo.findByUser(user);
		List<WishlistResponse>responses = new ArrayList<>();
		for(Wishlist wishlist : wishlists)
		{
			WishlistResponse response = new WishlistResponse();
			response.setWishlistid(wishlist.getId());
			response.setUsername(wishlist.getUser().getUsername());
			response.setProductName(wishlist.getProduct().getName());
			response.setPrice(wishlist.getProduct().getPrice());
			responses.add(response);
		}
		return responses;
	}
	public String removeFromWishlist(Integer wishlistId)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User user = userRepo.findByUsername(username)
				.orElseThrow(()->new RuntimeException("User Not Found"));
		
		Wishlist wishlist = wishlistRepo.findById(wishlistId)
				.orElseThrow(()->new RuntimeException("Wishlist Not Found"));
		if(!wishlist.getUser().getId().equals(user.getId()))
		{
			throw new RuntimeException("You are not allowed to delete this wishlist item");
		}
		wishlistRepo.delete(wishlist);
		return "Wishlist Item Deleted Successfully";
		
	}
	public String moveToCart(Integer wishlistId)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		
		//fetch the current user
		User user = userRepo.findByUsername(username)
				.orElseThrow(()->new RuntimeException("User Not Found"));
		
		//fetch wishlist record
		Wishlist wishlist = wishlistRepo.findById(wishlistId)
				.orElseThrow(()->new RuntimeException("Wishlist Not Found"));
		
		//Ownership check
		if(!wishlist.getUser().getId().equals(user.getId()))
		{
			throw new RuntimeException("You are not allowed to delete this wishlist item");
		}
		
		Product product = wishlist.getProduct();
		if(product.getQuantity()<=0)
		{
			throw new RuntimeException("Product is Out of Stock");
		}
		Optional<Cart>existingCart = cartRepo.findByUserAndProduct(user, product);
		
		//if product already exist add +1 to it
		if(existingCart.isPresent())
		{
			Cart cart = existingCart.get();
			cart.setQuantity(cart.getQuantity()+1);
			cartRepo.save(cart);
		}//otherwise create a new cart item
		else {
			Cart cart = new Cart();
			cart.setUser(user);
			cart.setProduct(product);
			cart.setQuantity(1);
			cartRepo.save(cart);
		}
		wishlistRepo.delete(wishlist);
		return "Product moved to cart successfully";
		
		
	}
	
}
