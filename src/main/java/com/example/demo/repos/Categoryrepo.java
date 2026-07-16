package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entites.Category;

public interface Categoryrepo  extends JpaRepository<Category,Integer>{
	

}
