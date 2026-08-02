package com.example.demo.repos;

import java.util.List;
import org.springframework.data.repository.query.Param;

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
	
	@Query("select distinct p.brand from Product p")
	List<String> getAllBrands();
	

	@Query("""
	SELECT p FROM Product p
	WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
	   OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%'))
	   OR LOWER(p.category.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
	""")
	List<Product> searchProducts(@Param("keyword") String keyword);
	
	
	
	
	
}
