package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entites.Category;
@Repository
public interface Categoryrepo  extends JpaRepository<Category,Integer>{
	

}
