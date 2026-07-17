package com.example.demo.DTO;

public class ProductRequest {

	private String name;
	private String brand;
	private Double price;
	private Integer quantity;
	private String description;
	private Integer categoryId;
	private Integer supplierId;
	public ProductRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductRequest(String name, String brand, Double price, Integer quantity, String description,
			Integer categoryId, Integer supplierId) {
		super();
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.categoryId = categoryId;
		this.supplierId = supplierId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	@Override
	public String toString() {
		return "ProductRequest [name=" + name + ", brand=" + brand + ", price=" + price + ", quantity=" + quantity
				+ ", description=" + description + ", categoryId=" + categoryId + ", supplierId=" + supplierId + "]";
	}
	
	
	
}
