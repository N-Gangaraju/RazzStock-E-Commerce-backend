package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entites.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

	List<Product> findByName(String name);
	List<Product>findByBrand(String name);
	List<Product>findByPriceBetween(double min,double max);
	List<Product>findByCategory_Name(String name);
	List<Product>findByBrandAndCategory_Name(String name,String category);
	List<Product>findByQuantityLessThan(Integer quantity);
	List<Product>findByQuantity(Integer quantity);
	List<Product>findByNameContainingIgnoreCase(String name);
	List<Product>findByBrandContainingIgnoreCase(String brand);
	List<Product>findByCategory_NameContainingIgnoreCase(String categoryname);
	List<Product>findAllByOrderByPriceAsc();
	List<Product>findAllByOrderByPriceDesc();
	@Query("SELECT COUNT(p) FROM Product p WHERE p.quantity < 5")
	long countLowStockProducts();
	
	
	
	
	
}
