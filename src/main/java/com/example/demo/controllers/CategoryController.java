package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entites.Category;
import com.example.demo.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/categories")
@Tag(name="Category API", description = "Manage Categories")
public class CategoryController {
	@Autowired
	private CategoryService service;
	
	@Operation(summary = "Add a Category")
	@PostMapping("/add")
	public Category addCategory(@RequestBody Category cat)
	{
		return service.addCategory(cat);
	}
	
	@Operation(summary= "Get All Categories")
	@GetMapping
	public List<Category> getAllCategories()
	{
		return service.getAll();
	}
	
	@Operation(summary = "Get Category By Id")
	@GetMapping("/{id}")
	public Category getCategoryById(@PathVariable Integer id)
	{
		return service.getById(id);
	}
	
	@Operation(summary = "Update Category")
	@PutMapping("/update/{id}")
	public Category updateCategory(@PathVariable Integer id,@RequestBody Category cat)
	{
		return service.update(id, cat);
	}
	
	@Operation(summary  = "Delete Category")
	@DeleteMapping("/delete/{id}")
	public Category deleteCategory(@PathVariable  Integer id)
	{
		return service.deleteById(id);
	}
	
}
