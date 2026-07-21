package com.example.demo.DTO;

public class ReviewRequest {

	private Integer productId;
	private Integer rating;
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
