package com.example.demo.controllers;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.ProductRequest;
import com.example.demo.DTO.ProductResponse;
import com.example.demo.Entites.Product;
import com.example.demo.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/products")
@Tag(name="Product API",description="Manage Products")
public class ProductController {
	@Autowired
	private ProductService serv;
	
	@Operation(summary = "Get Products with Pagination and Sorting")
	@GetMapping("/page")
	public Page<Product> getProducts(
	        @ParameterObject
	        @PageableDefault(size = 5) Pageable pageable) {

	    return serv.getAllProducts(pageable);
	}
	@Operation(summary = "Get Products By Searching the Name")
	@GetMapping("/search/name")
	public List<Product>searchByName(@RequestParam String name)
	{
		return serv.searchByName(name);
	}
	@Operation(summary = "Get Products By Searching the Brand")
	@GetMapping("/search/brand")
	public List<Product>searchByBrand(@RequestParam String name)
	{
		return serv.searchByBrand(name);
	}
	@Operation(summary = "Get Products By Searching the PriceBetween")
	@GetMapping("search/price")
	public List<Product>searchByPrice(@RequestParam Double min,@RequestParam Double max)
	{
		return serv.findByPriceBetween(min,max);
	}
	@Operation(summary = "Get Products By Searching the Category")
	@GetMapping("search/category")
	public List<Product>searchByCategory(@RequestParam String name)
	{
		return serv.findByCategory(name);
	}
	@Operation(summary = "Get Products By Searching Both Brands and Categories")
	@GetMapping("filter")
	public List<Product>filterProducts(@RequestParam String brand,@RequestParam String category)
	{
		return serv.findByBrandAndCategory(brand, category);
	} 
	@Operation(summary = "Get Products Count")
	@GetMapping("/count")
	public long getProductCount()
	{
		return serv.getProductCount();
	}
	@Operation(summary = "Get Low Stock Products ")
	@GetMapping("/stock/low")
	public List<Product>getLowStockProducts()
	{
		return serv.findByQuantityLessThan();
	}
	@Operation(summary = "Get Out of Stock Products")
	@GetMapping("/stock/out")
	public List<Product>getOutOfStockProducts()
	{
		return serv.findOutOfStockProducts();
	}
	//----------------------------------CURD---------------------------------------
	@Operation(summary="Add a Product")
	@PostMapping("/add")
	public Product addProduct(@RequestBody ProductRequest prod)
	{
		return serv.addProduct(prod); 
	}
	
	@Operation(summary = "Get All Products")
	@GetMapping
	public List<ProductResponse>getAllProducts()
	{
		return serv.readData();
	}
	
	@Operation(summary = "Get Product By Id")
	@GetMapping("/{id}")
	public ProductResponse getById(@PathVariable Integer id)
	{
		return serv.getProductById(id);
	}
	
	@Operation(summary = "Update Product")
	@PutMapping("/update/{id}")
	public Product updateById(@PathVariable Integer id,@RequestBody Product prod)
	{
		return serv.updateProduct(id, prod);
	}
	
	@Operation(summary = "Delete Product")
	@DeleteMapping("/delete/{id}")
	public Product deletebyId(@PathVariable Integer id)
	{
		return serv.deleteProduct(id);
	}

}
