package com.example.spring.boot.sales.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleFilterDTO {
	
	private Long sellerId;
	private Long customerId;
	private LocalDate startDate;
	private LocalDate endDate;

}
