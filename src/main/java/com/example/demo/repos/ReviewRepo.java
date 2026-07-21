package com.example.demo.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entites.Product;
import com.example.demo.Entites.Review;
import com.example.demo.Entites.User;
@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {
	
	List<Review> findByProduct(Product product);
	List<Review>findByUser(User user);
	Optional<Review>findByUserAndProduct(User user,Product product);
	
	@Query("SELECT AVG(r.rating) FROM Review r WHERE r.product.id = :productId")
	Double getAverageRating(Integer productId);
			

}
