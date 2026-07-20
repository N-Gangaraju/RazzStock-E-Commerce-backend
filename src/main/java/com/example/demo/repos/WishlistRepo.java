package com.example.demo.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entites.Product;
import com.example.demo.Entites.User;
import com.example.demo.Entites.Wishlist;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {
	
	List<Wishlist> findByUser(User user);
	Optional<Wishlist>findByUserAndProduct(User user,Product product);
}
