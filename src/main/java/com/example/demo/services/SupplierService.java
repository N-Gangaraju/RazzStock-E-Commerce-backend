package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entites.Supplier;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.repos.Supplierrepo;
@Service

public class SupplierService {
	@Autowired
	private Supplierrepo repo;

	public Supplier addSupplier(Supplier sup)
	{
		 return repo.save(sup);
	}
	public List<Supplier> getAllSuppliers()
	{
		return repo.findAll();
	}
	public Supplier getById(Integer id)
	{
		return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Supplier Not Found with id"+id));
	}
	public Supplier update(Integer id,Supplier sup)
	{
		if(repo.existsById(id))
		{
			sup.setId(id);
			return repo.save(sup);
		}
		else {
			return null;
		}
	}
	public Supplier deleteById(Integer id)
	{
		Supplier sup=repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Supplier Not Found With id"+id));
		if(repo.existsById(id))
		{
			repo.deleteById(id);
			return sup;
		}
		return null;
	}
}
