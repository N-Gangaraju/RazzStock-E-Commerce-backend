package com.example.demo.Entites;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;

@Entity
public class Wishlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private User user;
	@ManyToOne
	private Product product;

	private LocalDateTime addedAt;

	public Wishlist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Wishlist(Integer id, User user, Product product, LocalDateTime addedAt) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.addedAt = addedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LocalDateTime getAddedAt() {
		return addedAt;
	}

	public void setAddedAt(LocalDateTime addedAt) {
		this.addedAt = addedAt;
	}

	@Override
	public String toString() {
		return "Wishlist [id=" + id + ", user=" + user + ", product=" + product + ", addedAt=" + addedAt + "]";
	}
	
}
