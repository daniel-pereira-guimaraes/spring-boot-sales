package com.example.spring.boot.sales.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.boot.sales.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
}
