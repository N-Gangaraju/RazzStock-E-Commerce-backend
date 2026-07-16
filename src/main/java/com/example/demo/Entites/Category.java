package com.example.demo.Entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message="give catogory of product")
	private String name;
	private String description;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(Integer id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
	
}
