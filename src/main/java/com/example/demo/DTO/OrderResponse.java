package com.example.demo.DTO;

public class OrderResponse {

	private Integer orderId;
	private String username;
	private String productName;
	private Integer quantity;
	private double price;
	private double amount;
	private String status;
	private String orderedAt;
	public OrderResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderResponse(Integer orderId, String username, String productName, Integer quantity, double price,
			double amount, String status, String orderedAt) {
		super();
		this.orderId = orderId;
		this.username = username;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.amount = amount;
		this.status = status;
		this.orderedAt = orderedAt;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderedAt() {
		return orderedAt;
	}
	public void setOrderedAt(String orderedAt) {
		this.orderedAt = orderedAt;
	}
	@Override
	public String toString() {
		return "OrderResponse [orderId=" + orderId + ", username=" + username + ", productName=" + productName
				+ ", quantity=" + quantity + ", price=" + price + ", amount=" + amount + ", status=" + status
				+ ", orderedAt=" + orderedAt + "]";
	}
	
	
}
