package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entites.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

	List<Product> findByName(String name);
	List<Product>findByBrand(String name);
	List<Product>findByPriceBetween(double min,double max);
	List<Product>findByCategory_Name(String name);
}
