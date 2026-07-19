package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entites.Cart;
import com.example.demo.Entites.User;

@Repository
public interface CartRepo  extends JpaRepository<Cart, Integer>
{
	List<Cart>findByUser(User user);
}
