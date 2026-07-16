package com.example.demo.Entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message="Supplier name is Required")
	private String name;
	
	@NotBlank(message ="Email is required")
	@Email(message="enter valid email address")
	private String email;
	
	@Pattern(
		    regexp = "^[0-9]{10}$",
		    message = "Phone number must contain exactly 10 digits"
		)
	private String phone;
	
	@NotBlank(message="give a address")
	private String address;
	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Supplier(Integer id, String name, String email, String phone, String address) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address="
				+ address + "]";
	}
	
}
