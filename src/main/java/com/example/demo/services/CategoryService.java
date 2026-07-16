package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entites.Category;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.repos.Categoryrepo;

@Service
public class CategoryService {

	@Autowired
	private Categoryrepo repo;
	
	public Category addCategory(Category cat)
	{
		return repo.save(cat);
	}
	public List<Category> getAll()
	{
		return repo.findAll();
	}
	public Category getById(Integer id)
	{
		return repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category Not found with id: "+id));
	}
	public Category update(Integer id,Category cat)
	{
		if(repo.existsById(id))
		{
			cat.setId(id);
			return repo.save(cat);
		}
		return null;
	}
	public Category deleteById(Integer id)
	{
		Category cat = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category not found with id:"+id));

		if(cat != null)
		{
		    repo.delete(cat);
		    return cat;
		}

		return null;
	}
	
	
	
}
