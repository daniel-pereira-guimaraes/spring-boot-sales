package com.example.spring.boot.sales.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.spring.boot.sales.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query(
		"SELECT s FROM Sale s " +
		"JOIN Person seller ON seller = s.seller " +
		"JOIN Person customer ON customer = s.customer " +
		"WHERE (:sellerId IS NULL OR seller.id = :sellerId) " +
		"  AND (:customerId IS NULL OR customer.id = :customerId) " +
		"  AND (:startDate IS NULL OR s.date >= :startDate) " +
		"  AND (:endDate IS NULL OR s.date <= :endDate)")
	List<Sale> findByFilter(
		@Param("sellerId") Long sellerId,
		@Param("customerId") Long customerId,
		@Param("startDate") LocalDate startDate,
		@Param("endDate") LocalDate endDate);
	
}
