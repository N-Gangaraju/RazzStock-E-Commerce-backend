package com.example.demo.Entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message="Product name is required")
	private String name;
	
	@NotBlank(message="Brand is Required")
	private String brand;
	
	@Positive(message="price must be greater Than 0")
	private Double price;
	
	@PositiveOrZero(message="quantity cannot be negative")
	private Integer quantity;
	private String description;
	@ManyToOne
	private Category category;
	@ManyToOne
	private Supplier supplier;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(Integer id, String name, String brand, Double price, Integer quantity, String description,
			Category category, Supplier supplier) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.category = category;
		this.supplier = supplier;
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", brand=" + brand + ", price=" + price + ", quantity="
				+ quantity + ", description=" + description + ", category=" + category + ", supplier=" + supplier + "]";
	}
	
}
