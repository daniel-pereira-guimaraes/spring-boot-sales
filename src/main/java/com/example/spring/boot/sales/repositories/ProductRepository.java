package com.example.spring.boot.sales.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.boot.sales.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findAllByOrderByDescription();
	public Optional<Product> findByGtin(String taxId);

}
