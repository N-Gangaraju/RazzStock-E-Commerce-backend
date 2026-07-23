package com.example.demo.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ReviewRequest {

	private Integer productId;
	@Min(1)
	@Max(5)
	private Integer rating;
	
	@NotBlank
	private String comment;
	public ReviewRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReviewRequest(Integer productId, Integer rating, String comment) {
		super();
		this.productId = productId;
		this.rating = rating;
		this.comment = comment;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "ReviewRequest [productId=" + productId + ", rating=" + rating + ", comment=" + comment + "]";
	}
	
	
}
