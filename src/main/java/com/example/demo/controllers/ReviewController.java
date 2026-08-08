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

import com.example.demo.DTO.ReviewRequest;
import com.example.demo.DTO.ReviewResponse;
import com.example.demo.services.ReviewService;

import io.swagger.v3.oas.annotations.Operation;



@RestController
@RequestMapping("/reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@Operation(summary = "Add review")
	@PostMapping("/add")
	public ReviewResponse addReview(@RequestBody ReviewRequest request)
	{
		return reviewService.addReview(request);
	}
	
	@Operation(summary = "Get product reviews")
	@GetMapping("/product/{productId}")
	public List<ReviewResponse> getReviewsByProduct(@PathVariable Integer productId)
	{
	        return reviewService.getReviewByProduct(productId);
	}
	
	@Operation(summary = "Get Average product rating")
	@GetMapping("product/{productId}/rating")
	public Double getAverageRating(@PathVariable Integer productId)
	{
		return reviewService.getAverageRating(productId);
	}
	@Operation(summary = "Update review")
	@PutMapping("/update/{reviewId}")
	public ReviewResponse updateReview(
	        @PathVariable Integer reviewId,
	        @RequestBody ReviewRequest request)
	{
	    return reviewService.updateReview(reviewId, request);
	}

	@Operation(summary = "Delete review")
	@DeleteMapping("/delete/{reviewId}")
	public String deleteReview(@PathVariable Integer reviewId)
	{
	    return reviewService.deleteReview(reviewId);
	}
	
	@Operation(summary = "Get all reviews for admin")
	@GetMapping
	public List<ReviewResponse> getAllReviews()
	{
	    return reviewService.getAllReviews();
	}
	
	
}
