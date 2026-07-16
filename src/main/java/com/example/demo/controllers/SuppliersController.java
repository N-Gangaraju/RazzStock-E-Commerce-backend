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
import com.example.demo.Entites.Supplier;
import com.example.demo.services.SupplierService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/suppliers")
@Tag(name="Supplier API",description="Manage Suppliers")
public class SuppliersController {
	@Autowired
	private SupplierService service;
	
	@Operation(summary="Add a Supplier")
	@PostMapping
	public Supplier addSupplier(@RequestBody Supplier supplier)
	{
		return service.addSupplier(supplier);
	}
	
	@Operation(summary="Get All Suppliers")
	@GetMapping
	public List<Supplier> getAllSupplierss()
	{
		return service.getAllSuppliers();
	}
	
	@Operation(summary="Get Supplier By Id")
	@GetMapping("/{id}")
	public Supplier getSupplierById(@PathVariable Integer id)
	{
		return service.getById(id);
	}
	
	@Operation(summary="Update Supplier")
	@PutMapping("/{id}")
	public Supplier updateSupplier(@PathVariable Integer id,@RequestBody Supplier supplier)
	{
		return service.update(id, supplier);
	}
	
	@Operation(summary = "Delete Supplier")
	@DeleteMapping("/{id}")
	public Supplier deleteSupplier(@PathVariable  Integer id)
	{
		return service.deleteById(id);
	}

}
