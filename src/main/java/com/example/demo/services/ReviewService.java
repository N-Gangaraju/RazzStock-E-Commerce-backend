package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ReviewRequest;
import com.example.demo.DTO.ReviewResponse;
import com.example.demo.Entites.Product;
import com.example.demo.Entites.Review;
import com.example.demo.Entites.User;
import com.example.demo.repos.ProductRepo;
import com.example.demo.repos.ReviewRepo;
import com.example.demo.repos.UserRepo;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepo reviewRepo;
	
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private UserRepo userRepo;
	
	public ReviewResponse addReview(ReviewRequest request)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		User user = userRepo.findByUsername(username)
		        .orElseThrow(() -> new RuntimeException("User Not Found"));
		
		Product product = productRepo.findById(request.getProductId())
		        .orElseThrow(() -> new RuntimeException("Product Not Found"));
		
		Optional<Review> existingReview =
		        reviewRepo.findByUserAndProduct(user, product);

		if(existingReview.isPresent()) {
		    throw new RuntimeException("You already reviewed this product");
		}
		
		Review review = new Review();

		review.setUser(user);
		review.setProduct(product);
		review.setRating(request.getRating());
		review.setComment(request.getComment());
		review.setReviewedAt(LocalDateTime.now());
		
		Review savedReview = reviewRepo.save(review);
		
		//convert to response
		ReviewResponse response = new ReviewResponse();

		response.setReviewId(savedReview.getId());
		response.setUsername(savedReview.getUser().getUsername());
		response.setProductName(savedReview.getProduct().getName());
		response.setRating(savedReview.getRating());
		response.setComment(savedReview.getComment());
		response.setReviewdAt(savedReview.getReviewedAt().toString());

		return response;
		
	}
	//get reviews by products
	public List<ReviewResponse>getReviewByProduct(Integer productId)
	{
		Product product = productRepo.findById(productId)
		        .orElseThrow(() -> new RuntimeException("Product Not Found"));
		
		List<Review>reviews = reviewRepo.findByProduct(product);
		//convert to response
		List<ReviewResponse> responses = new ArrayList<>();

		for(Review review : reviews)
		{
		    ReviewResponse response = new ReviewResponse();

		    response.setReviewId(review.getId());
		    response.setUsername(review.getUser().getUsername());
		    response.setProductName(review.getProduct().getName());
		    response.setRating(review.getRating());
		    response.setComment(review.getComment());
		    response.setReviewdAt(review.getReviewedAt().toString());

		    responses.add(response);
		}

		return responses;
	}
	public Double getAverageRating(Integer productId)
	{
	    productRepo.findById(productId)
	            .orElseThrow(() -> new RuntimeException("Product Not Found"));

	    Double average = reviewRepo.getAverageRating(productId);

	    if (average == null)
	    {
	        return 0.0;
	    }

	    return average;
	}
	public ReviewResponse updateReview(Integer reviewId, ReviewRequest request)
	{
	    Authentication authentication =
	            SecurityContextHolder.getContext().getAuthentication();

	    String username = authentication.getName();

	    User user = userRepo.findByUsername(username)
	            .orElseThrow(() -> new RuntimeException("User Not Found"));

	    Review review = reviewRepo.findById(reviewId)
	            .orElseThrow(() -> new RuntimeException("Review Not Found"));

	    // Only the review owner can update
	    if (!review.getUser().getId().equals(user.getId()))
	    {
	        throw new RuntimeException("You are not allowed to update this review");
	    }

	    review.setRating(request.getRating());
	    review.setComment(request.getComment());

	    Review updatedReview = reviewRepo.save(review);

	    ReviewResponse response = new ReviewResponse();
	    response.setReviewId(updatedReview.getId());
	    response.setUsername(updatedReview.getUser().getUsername());
	    response.setProductName(updatedReview.getProduct().getName());
	    response.setRating(updatedReview.getRating());
	    response.setComment(updatedReview.getComment());
	    response.setReviewdAt(updatedReview.getReviewedAt().toString());

	    return response;
	}
	public String deleteReview(Integer reviewId)
	{
	    Review review = reviewRepo.findById(reviewId)
	            .orElseThrow(() -> new RuntimeException("Review Not Found"));

	    Authentication authentication =
	            SecurityContextHolder.getContext().getAuthentication();

	    boolean isAdmin = authentication.getAuthorities()
	            .stream()
	            .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));


	    if(!isAdmin)
	    {
	        String username = authentication.getName();

	        User user = userRepo.findByUsername(username)
	                .orElseThrow(() -> new RuntimeException("User Not Found"));


	        // Customer can delete only own review
	        if(!review.getUser().getId().equals(user.getId()))
	        {
	            throw new RuntimeException("You are not allowed to delete this review");
	        }
	    }


	    reviewRepo.delete(review);

	    return "Review Deleted Successfully";
	}
	public List<ReviewResponse> getAllReviews()
	{
	    return reviewRepo.findAll()
	            .stream()
	            .map(review -> {

	                ReviewResponse response = new ReviewResponse();

	                response.setReviewId(review.getId());
	                response.setRating(review.getRating());
	                response.setComment(review.getComment());

	                response.setProductName(
	                    review.getProduct().getName()
	                );

	                response.setUsername(
	                    review.getUser().getUsername()
	                );

	                return response;

	            })
	            .toList();
	}
	
}
