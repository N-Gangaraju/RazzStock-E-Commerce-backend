package com.example.demo.DTO;

import java.time.LocalDateTime;

public class RecentOrderResponse {

	private Integer orderId;
	private String customerName;
	private String productName;
	private Integer quantity;
	private Double amount;
	private String status;
	private LocalDateTime orderedAt;
	public RecentOrderResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RecentOrderResponse(Integer orderId, String customerName, String productName, Integer quantity,
			Double amount, String status, LocalDateTime orderedAt) {
		super();
		this.orderId = orderId;
		this.customerName = customerName;
		this.productName = productName;
		this.quantity = quantity;
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getOrderedAt() {
		return orderedAt;
	}
	public void setOrderedAt(LocalDateTime orderedAt) {
		this.orderedAt = orderedAt;
	}
	
	
}
