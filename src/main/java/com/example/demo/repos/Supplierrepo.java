package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entites.Supplier;
@Repository
public interface Supplierrepo  extends JpaRepository<Supplier, Integer>{

}
