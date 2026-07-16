package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entites.Product;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.repos.ProductRepo;
@Service
public class ProductService {
	
	@Autowired
	private ProductRepo repo;
	
	public Page<Product>getAllProducts(Pageable pageable)
	{
		return repo.findAll(pageable);
	}
	public List<Product>searchByName(String name)
	{
		return repo.findByName(name);
	}
	public List<Product>searchByBrand(String name)
	{
		return repo.findByBrand(name);
	}
	public List<Product>findByPriceBetween(double min,double max)
	{
		return repo.findByPriceBetween(min, max);
	}
	public List<Product>findByCategory(String name)
	{
		return repo.findByCategory_Name(name);
	}
	//-----------------------------CRUD---------------------------------------------
	public Product addProduct(Product prod)
	{
		return repo.save(prod);
	}
	public List<Product> readData()
	{
		return repo.findAll();
	}
	public Product getProductById(Integer id) 
	{
	    return repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found with id:"+id));
	}
	public Product updateProduct(Integer id, Product product) 
	{

	    if (repo.existsById(id)) 
	    {
	        product.setId(id);
	        return repo.save(product);
	    }

	    return null;
	}
	public Product deleteProduct(Integer id)
	{
		Product prod=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found with id:"+id));
		if(prod != null)
		{
		    repo.delete(prod);
		    return prod;
		}

		return null;
	}
	
	

}
