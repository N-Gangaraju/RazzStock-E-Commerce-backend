package com.example.demo.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entites.Cart;
import com.example.demo.Entites.Product;
import com.example.demo.Entites.User;

@Repository
public interface CartRepo  extends JpaRepository<Cart, Integer>
{
	List<Cart>findByUser(User user);
	Optional<Cart> findByUserAndProduct(User user,Product product);
}
