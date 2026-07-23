package com.example.demo.DTO;

public class TopSellingProductsResponse {
	
	private String productName;
	private Long quantitySold;
	public TopSellingProductsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TopSellingProductsResponse(String productName, Long quantitySold) {
		super();
		this.productName = productName;
		this.quantitySold = quantitySold;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getQuantitySold() {
		return quantitySold;
	}
	public void setQuantitySold(Long quantitySold) {
		this.quantitySold = quantitySold;
	}
	@Override
	public String toString() {
		return "TopSellingProductsResponse [productName=" + productName + ", quantitySold=" + quantitySold + "]";
	}
	

}
