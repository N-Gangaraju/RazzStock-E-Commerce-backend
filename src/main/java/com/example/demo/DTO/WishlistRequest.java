package com.example.demo.DTO;

public class WishlistRequest {
	
	private Integer productId;

	public WishlistRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WishlistRequest(Integer productId) {
		super();
		this.productId = productId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	

}
