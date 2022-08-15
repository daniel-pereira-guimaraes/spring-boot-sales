package com.example.spring.boot.sales.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class SaleDTO {
	private Long id;
	private LocalDate date;
	private SaleSellerDTO seller;
	private SaleCustomerDTO customer;
	private String comment;
}
