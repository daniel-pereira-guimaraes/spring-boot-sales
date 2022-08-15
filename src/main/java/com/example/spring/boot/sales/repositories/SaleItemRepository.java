package com.example.spring.boot.sales.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.boot.sales.entities.SaleItem;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
	
	List<SaleItem> findBySaleId(Long saleId);
	
}
