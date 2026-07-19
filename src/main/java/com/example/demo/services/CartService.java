package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CartRequest;
import com.example.demo.DTO.CartResponse;
import com.example.demo.DTO.UpdateCartRequest;
import com.example.demo.Entites.Cart;
import com.example.demo.Entites.Order;
import com.example.demo.Entites.Product;
import com.example.demo.Entites.User;
import com.example.demo.Enum.OrderStatus;
import com.example.demo.repos.CartRepo;
import com.example.demo.repos.OrderRepo;
import com.example.demo.repos.ProductRepo;
import com.example.demo.repos.UserRepo;

@Service
public class CartService {
	
	@Autowired
	private CartRepo cartRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private OrderRepo orderrepo;
	
	public CartResponse addToCart(CartRequest request)
	{
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();

	    User user = userRepo.findByUsername(username)
	            .orElseThrow(() -> new RuntimeException("User Not Found"));

	    Product product = productRepo.findById(request.getProductId())
	            .orElseThrow(() -> new RuntimeException("Product Not Found"));

	    // Check if product already exists in user's cart
	    Optional<Cart> existingCart = cartRepo.findByUserAndProduct(user, product);

	    if (existingCart.isPresent())
	    {
	        Cart cart = existingCart.get();

	        int newQuantity = cart.getQuantity() + request.getQuantity();

	        if (newQuantity > product.getQuantity())
	        {
	            throw new RuntimeException(
	                    "Only " + product.getQuantity() + " items are available.");
	        }

	        cart.setQuantity(newQuantity);

	        Cart savedCart = cartRepo.save(cart);

	        CartResponse response = new CartResponse();
	        response.setCartId(savedCart.getId());
	        response.setUsername(savedCart.getUser().getUsername());
	        response.setProductName(savedCart.getProduct().getName());
	        response.setQuantity(savedCart.getQuantity());
	        response.setPrice(savedCart.getProduct().getPrice());
	        response.setAmount(savedCart.getProduct().getPrice() * savedCart.getQuantity());

	        return response;
	    }

	    // Check stock for new cart item
	    if (request.getQuantity() > product.getQuantity())
	    {
	        throw new RuntimeException(
	                "Only " + product.getQuantity() + " items are available.");
	    }

	    // Create new cart item
	    Cart cart = new Cart();
	    cart.setUser(user);
	    cart.setProduct(product);
	    cart.setQuantity(request.getQuantity());

	    Cart savedCart = cartRepo.save(cart);

	    CartResponse response = new CartResponse();
	    response.setCartId(savedCart.getId());
	    response.setUsername(savedCart.getUser().getUsername());
	    response.setProductName(savedCart.getProduct().getName());
	    response.setQuantity(savedCart.getQuantity());
	    response.setPrice(savedCart.getProduct().getPrice());
	    response.setAmount(savedCart.getProduct().getPrice() * savedCart.getQuantity());

	    return response;
	}
	public List<CartResponse>myCart()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User user = userRepo.findByUsername(username)
				.orElseThrow(()-> new RuntimeException("User Not Found"));
		List<Cart> carts = cartRepo.findByUser(user);
		//converting above list into response list
		List<CartResponse> responses = new ArrayList<>();
		for(Cart cart : carts)
		{
			CartResponse response = new CartResponse();
			response.setCartId(cart.getId());
			response.setUsername(cart.getUser().getUsername());
			response.setProductName(cart.getProduct().getName());
			response.setQuantity(cart.getQuantity());
			response.setPrice(cart.getProduct().getPrice());
			response.setAmount(cart.getProduct().getPrice()*cart.getQuantity());
			responses.add(response);
			
		}
		return responses;
	}
	public CartResponse updateCart(Integer cartId,UpdateCartRequest request)
	{
		Authentication authentication =
	            SecurityContextHolder.getContext().getAuthentication();

	    String username = authentication.getName();

	    User user = userRepo.findByUsername(username)
	            .orElseThrow(() -> new RuntimeException("User Not Found"));
	    
	    Cart cart = cartRepo.findById(cartId).orElseThrow(()-> new RuntimeException("Cart Not Found"));
	    
	    if(!cart.getUser().getId().equals(user.getId())) 
	    {
	    	throw new RuntimeException("You are not allowed to update this cart");
	    }
	    	cart.setQuantity(request.getQuantity());
	    	Cart updatedCart = cartRepo.save(cart);
	    	CartResponse response = new CartResponse();
	    	response.setCartId(updatedCart.getId());
	    	response.setUsername(updatedCart.getUser().getUsername());
	    	response.setProductName(updatedCart.getProduct().getName());
	    	response.setQuantity(updatedCart.getQuantity());
	    	response.setPrice(updatedCart.getProduct().getPrice());
	    	response.setAmount(updatedCart.getProduct().getPrice()*updatedCart.getQuantity());
	    	return response;   
	}
	public String deleteCart(Integer cartId)
	{
	    Authentication authentication =
	            SecurityContextHolder.getContext().getAuthentication();

	    String username = authentication.getName();

	    User user = userRepo.findByUsername(username)
	            .orElseThrow(() -> new RuntimeException("User Not Found"));

	    Cart cart = cartRepo.findById(cartId)
	            .orElseThrow(() -> new RuntimeException("Cart Not Found"));
	    
	    if (!cart.getUser().getId().equals(user.getId())) 
	    {
	        throw new RuntimeException("You are not allowed to delete this cart");
	    }
	    cartRepo.delete(cart);
	    return " Cart Item Deleted Successfully";
	}
	public String checkout()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();

	    User user = userRepo.findByUsername(username)
	            .orElseThrow(() -> new RuntimeException("User Not Found"));

	    List<Cart> carts = cartRepo.findByUser(user);
	    if(carts.isEmpty()) {
	    	throw new RuntimeException("Cart is Empty");
	    }
	    for (Cart cart : carts)
	    {
	    	Product product = cart.getProduct();
	    	int available = product.getQuantity();
	    	int requested = cart.getQuantity();
	    	if(requested > available )
	    	{
	    		throw new RuntimeException(product.getName()+" is out of stock");
	    	}
	    	//System.out.println("Before update;"+product.getQuantity());
	    	product.setQuantity(available - requested);
	    	//System.out.println("after update:"+product.getQuantity());
	    	productRepo.save(product);
	    	
	        Order order = new Order();

	        order.setUser(user);
	        order.setProduct(product);

	        order.setQuantity(requested);

	        order.setPrice(product.getPrice());

	        order.setAmount(product.getPrice() * requested);

	        order.setStatus(OrderStatus.PENDING);

	        order.setOrderedAt(LocalDateTime.now());

	        orderrepo.save(order);
	    }
	    cartRepo.deleteAll(carts);
	    return "Order Placed Successfully";
	}
	
	
}
