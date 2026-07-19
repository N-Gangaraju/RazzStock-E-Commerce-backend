package com.example.demo.DTO;

public class CartRequest 
{
	private Integer productId;
	private Integer quantity;
	public CartRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartRequest(Integer productId, Integer quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "CartRequest [productId=" + productId + ", quantity=" + quantity + "]";
	}
	

}
