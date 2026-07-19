package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entites.Order;
import com.example.demo.Entites.User;
@Repository
public interface OrderRepo  extends JpaRepository<Order, Integer>{

	List<Order> findByUser(User user);
}
