package com.example.demo.DTO;

public class CartResponse {

	private Integer cartId;
	private String username;
	private String productName;
	private Double price;
	private Integer quantity;
	private Double amount;
	public CartResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CartResponse(Integer cartId, String username, String productName, Double price, Integer quantity,
			Double amount) {
		super();
		this.cartId = cartId;
		this.username = username;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.amount = amount;
	}

	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "CartResponse [cartId=" + cartId + ", username=" + username + ", productName=" + productName + ", price="
				+ price + ", quantity=" + quantity + ", amount=" + amount + "]";
	}

	
	
	
}
