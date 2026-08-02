package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entites.Order;
import com.example.demo.Entites.User;
@Repository
public interface OrderRepo  extends JpaRepository<Order, Integer>{

	List<Order> findByUser(User user);
	
	@Query("SELECT COALESCE(SUM(o.amount),0) FROM Order o")
	Double getTotalRevenue();
	
	 @Query("SELECT COUNT(p) FROM Product p WHERE p.quantity < 5")
	    long countLowStockProducts();
	 
	@Query("""
			SELECT o.product.name, SUM(o.quantity)
			FROM Order o
			GROUP BY o.product.name
			ORDER BY SUM(o.quantity) DESC
			""")
			List<Object[]> getTopSellingProducts();
			
			List<Order>findTop10ByOrderByOrderedAtDesc();
			
			@Query(value = """
					SELECT MONTHNAME(ordered_at) AS month,
					       SUM(amount) AS revenue
					FROM orders
					GROUP BY MONTH(ordered_at), MONTHNAME(ordered_at)
					ORDER BY MONTH(ordered_at)
					""", nativeQuery = true)
					List<Object[]> getMonthlyRevenue();
			
					
					@Query("""
							SELECT o.user.username,
							       COUNT(o),
							       SUM(o.amount)
							FROM Order o
							GROUP BY o.user.username
							ORDER BY SUM(o.amount) DESC
							""")
							List<Object[]> getTopCustomers();
					
	
}
