package com.example.demo.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	
	private static final Logger logger =
	        LoggerFactory.getLogger(ProductService.class);
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
	public List<String> getAllBrands() {
	    return repo.getAllBrands();
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
	    response.setImageUrl(
	            "http://localhost:8080/uploads/" + product.getImageUrl()
	    );

	    if(product.getCategory()!=null){
	        response.setCategoryId(product.getCategory().getId());
	        response.setCategoryname(product.getCategory().getName());
	    }

	    if(product.getSupplier()!=null){
	        response.setSupplierId(product.getSupplier().getId());
	        response.setSuppliername(product.getSupplier().getName());
	    }

	    response.setCreatedAt(
	            product.getCreatedAt()!=null
	            ? product.getCreatedAt().toString()
	            : null
	    );

	    response.setUpdatedAt(
	            product.getUpdatedAt()!=null
	            ? product.getUpdatedAt().toString()
	            : null
	    );
	    	
	    return response;
	}
	public List<ProductResponse> searchProducts(String keyword)
	{
		List<Product>products = repo.searchProducts(keyword);
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
	    
	    logger.info("Adding product {}", request.getName());
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
	    Product product = repo.findById(id).orElseThrow(() -> {

            logger.error("Product not found with id {}", id);

            return new RuntimeException("Product Not Found");
        });
	    return convertToResponse(product);
	}
	
	public Product updateProduct(Integer id, ProductRequest request)
	{
	    Product product = repo.findById(id)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Product not found"));

	    Category category = categoryrepo.findById(request.getCategoryId())
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Category not found"));

	    Supplier supplier = supplierrepo.findById(request.getSupplierId())
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Supplier not found"));

	    product.setName(request.getName());
	    product.setBrand(request.getBrand());
	    product.setPrice(request.getPrice());
	    product.setQuantity(request.getQuantity());
	    product.setDescription(request.getDescription());

	    product.setCategory(category);
	    product.setSupplier(supplier);

	    logger.info("Product updated");

	    return repo.save(product);
	}
	public Product deleteProduct(Integer id)
	{
		logger.info("Product deleted");
		Product prod=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found with id:"+id));
		if(prod != null)
		{
		    repo.delete(prod);
		    return prod;
		}

		return null;
	}
	public ProductResponse uploadImage(Integer productId, MultipartFile file) throws IOException {

	    Product product = repo.findById(productId)
	            .orElseThrow(() -> new RuntimeException("Product Not Found"));

	    // Absolute path inside your project
	    String uploadPath = System.getProperty("user.dir") + "/uploads/";

	    File directory = new File(uploadPath);

	    if (!directory.exists()) {
	        directory.mkdirs();
	    }

	    String fileName = file.getOriginalFilename();

	    File destination = new File(uploadPath + fileName);

	    file.transferTo(destination);

	    product.setImageUrl(fileName);

	    Product savedProduct = repo.save(product);

	    return convertToResponse(savedProduct);
	}
	

}
