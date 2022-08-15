package com.example.spring.boot.sales.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.spring.boot.sales.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findAllByOrderByDescription();
	public Optional<Product> findByGtin(String taxId);
	
	@Modifying
	@Query("UPDATE Product p SET p.quantity = p.quantity + :increment WHERE p.id = :id")
	public int incQuantity(@Param("id") Long id, @Param("increment") Long increment);
	
}
