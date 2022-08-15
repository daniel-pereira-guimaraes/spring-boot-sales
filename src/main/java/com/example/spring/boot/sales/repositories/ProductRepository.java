package com.example.spring.boot.sales.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.spring.boot.sales.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findAllByOrderByDescription();
	Optional<Product> findByGtin(String taxId);
	
	@Modifying
	@Query("UPDATE Product p SET p.quantity = p.quantity + :increment WHERE p.id = :id")
	int incQuantity(@Param("id") Long id, @Param("increment") Long increment);
	
	@Query("SELECT p.quantity FROM Product p WHERE p.id = ?1")
	Long getQuantity(Long id);
	
}
