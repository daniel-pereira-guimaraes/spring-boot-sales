package com.example.spring.boot.sales.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface SaleSummaryByDateDTO {
	
	LocalDate getDate();
	BigDecimal getMin();
	BigDecimal getMax();
	BigDecimal getTotal();
	
}
