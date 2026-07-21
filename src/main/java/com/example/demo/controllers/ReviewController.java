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



@RestController
@RequestMapping("/reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/add")
	public ReviewResponse addReview(@RequestBody ReviewRequest request)
	{
		return reviewService.addReview(request);
	}
	
	//get review of a product
	@GetMapping("/product/{productId}")
	public List<ReviewResponse> getReviewsByProduct(@PathVariable Integer productId)
	{
	        return reviewService.getReviewByProduct(productId);
	}
	
	//average rating 
	@GetMapping("product/{productId}/rating")
	public Double getAverageRating(@PathVariable Integer productId)
	{
		return reviewService.getAverageRating(productId);
	}
	@PutMapping("/update/{reviewId}")
	public ReviewResponse updateReview(
	        @PathVariable Integer reviewId,
	        @RequestBody ReviewRequest request)
	{
	    return reviewService.updateReview(reviewId, request);
	}

	@DeleteMapping("/delete/{reviewId}")
	public String deleteReview(@PathVariable Integer reviewId)
	{
	    return reviewService.deleteReview(reviewId);
	}
	
	
}
