package com.example.demo.DTO;

public class ProductResponse {
	private Integer id;
	private String name;
	private String brand;
	private Double price;
	private Integer quantity;
	private String description;
	private String categoryname;
	private String suppliername;
	private String createdAt;
	private String updatedAt;
	private String imageUrl;

	public ProductResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProductResponse(Integer id, String name, String brand, Double price, Integer quantity, String description,
			String categoryname, String suppliername, String createdAt, String updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.categoryname = categoryname;
		this.suppliername = suppliername;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "ProductResponse [id=" + id + ", name=" + name + ", brand=" + brand + ", price=" + price + ", quantity="
				+ quantity + ", description=" + description + ", categoryname=" + categoryname + ", suppliername="
				+ suppliername + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	

}
