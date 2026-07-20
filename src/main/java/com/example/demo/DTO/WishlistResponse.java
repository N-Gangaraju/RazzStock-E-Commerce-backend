package com.example.demo.DTO;

public class WishlistResponse {

	private Integer wishlistid;
	private String username;
	private String productName;
	private double price;
	public WishlistResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WishlistResponse(Integer wishlistid, String username, String productName, double price) {
		super();
		this.wishlistid = wishlistid;
		this.username = username;
		this.productName = productName;
		this.price = price;
	}
	public Integer getWishlistid() {
		return wishlistid;
	}
	public void setWishlistid(Integer wishlistid) {
		this.wishlistid = wishlistid;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "WishlistResponse [wishlistid=" + wishlistid + ", username=" + username + ", productName=" + productName
				+ ", price=" + price + "]";
	}
	
	
}
