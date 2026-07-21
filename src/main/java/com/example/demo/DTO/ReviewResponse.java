package com.example.demo.DTO;

public class ReviewResponse {

	private Integer reviewId;
	private String username;
	private String productName;
	private Integer rating;
	private String comment;
	private String reviewdAt;
	public ReviewResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReviewResponse(Integer reviewId, String username, String productName, Integer rating, String comment,
			String reviewdAt) {
		super();
		this.reviewId = reviewId;
		this.username = username;
		this.productName = productName;
		this.rating = rating;
		this.comment = comment;
		this.reviewdAt = reviewdAt;
	}
	public Integer getReviewId() {
		return reviewId;
	}
	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public String getReviewdAt() {
		return reviewdAt;
	}
	public void setReviewdAt(String reviewdAt) {
		this.reviewdAt = reviewdAt;
	}
	@Override
	public String toString() {
		return "ReviewResponse [reviewId=" + reviewId + ", username=" + username + ", productName=" + productName
				+ ", rating=" + rating + ", comment=" + comment + ", reviewdAt=" + reviewdAt + "]";
	}
	
	
}
