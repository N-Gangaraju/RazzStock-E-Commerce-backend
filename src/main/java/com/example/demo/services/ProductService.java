package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ProductRequest;
import com.example.demo.DTO.ProductResponse;
import com.example.demo.Entites.Category;
import com.example.demo.Entites.Product;
import com.example.demo.Entites.Supplier;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.repos.Categoryrepo;
import com.example.demo.repos.ProductRepo;
import com.example.demo.repos.Supplierrepo;
@Service
public class ProductService {
	
	@Autowired
	private ProductRepo repo;
	@Autowired
	private Categoryrepo categoryrepo;
	@Autowired
	private Supplierrepo supplierrepo;
	
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
	public List<Product>findByBrandAndCategory(String name,String category)
	{
		return repo.findByBrandAndCategory_Name(name, category);
	}
	public long getProductCount()
	{
		return repo.count();
	}
	public List<Product>findByQuantityLessThan()
	{
		return repo.findByQuantityLessThan(5);
	}
	public List<Product>findOutOfStockProducts()
	{
		return repo.findByQuantity(0);
	}
	private ProductResponse convertToResponse(Product product)
	{
		ProductResponse response = new ProductResponse();
		response.setId(product.getId());
	    response.setName(product.getName());
	    response.setBrand(product.getBrand());
	    response.setPrice(product.getPrice());
	    response.setQuantity(product.getQuantity());
	    response.setDescription(product.getDescription());

	    response.setCategoryname(product.getCategory().getName());
	    response.setSuppliername(product.getSupplier().getName());

	    response.setCreatedAt(
	    	    product.getCreatedAt() != null
	    	        ? product.getCreatedAt().toString()
	    	        : null
	    	);

	    	response.setUpdatedAt(
	    	    product.getUpdatedAt() != null
	    	        ? product.getUpdatedAt().toString()
	    	        : null
	    	);

	    return response;
	}
	public List<ProductResponse> searchProducts(String keyword)
	{
		List<Product>products = repo.findByNameContainingIgnoreCase(keyword);
		List<ProductResponse>responses = new ArrayList<>();
		
		for(Product product : products)
		{
		    responses.add(convertToResponse(product));
		}
		
		return responses;
	}
	public List<ProductResponse>SearchByBrand(String brand)
	{
		List<Product>products = repo.findByBrandContainingIgnoreCase(brand);
		List<ProductResponse> responses = new ArrayList<>();
		for(Product product : products)
		{
			responses.add(convertToResponse(product));
		}
		return responses;
	}
	public List<ProductResponse>SearchByCategory(String category)
	{
		List<Product>products = repo.findByCategory_NameContainingIgnoreCase(category);
		List<ProductResponse>responses = new ArrayList<>();
		for(Product product : products)
		{
			responses.add(convertToResponse(product));
		}
		return responses;
	}
	public List<ProductResponse>searchByAsce()
	{
		List<Product> products = repo.findAllByOrderByPriceAsc();
		List<ProductResponse>responses = new ArrayList<>();
		for(Product product : products)
		{
			responses.add(convertToResponse(product));
		}
		return responses;
	}
	public List<ProductResponse>searchByDesc()
	{
		List<Product> products = repo.findAllByOrderByPriceDesc();
		List<ProductResponse>responses = new ArrayList<>();
		for(Product product : products)
		{
			responses.add(convertToResponse(product));
		}
		return responses;
	}
	//-----------------------------CRUD---------------------------------------------
	public Product addProduct(ProductRequest request)
	{
	    Category category = categoryrepo.findById(request.getCategoryId())
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Category not found with id: " + request.getCategoryId()));

	    Supplier supplier = supplierrepo.findById(request.getSupplierId())
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Supplier not found with id: " + request.getSupplierId()));
	    Product product=new Product();
	    product.setName(request.getName());
	    product.setBrand(request.getBrand());
	    product.setPrice(request.getPrice());
	    product.setQuantity(request.getQuantity());
	    product.setDescription(request.getDescription());

	    product.setCategory(category);
	    product.setSupplier(supplier);

	    return repo.save(product);
	    
	}
	public List<ProductResponse> readData()
	{
		return repo.findAll()
				.stream()
				.map(product -> convertToResponse(product))
				.toList();
	}
	public ProductResponse getProductById(Integer id) 
	{
	    Product product = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found with id:"+id));
	    return convertToResponse(product);
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
