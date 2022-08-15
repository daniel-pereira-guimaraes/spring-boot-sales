package com.example.spring.boot.sales.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class SaleItemDTO {
	private Long id;
	private SaleProductDTO product;
	private Long quantity;
	private BigDecimal price;
	
	public BigDecimal getTotal() {
		return price.multiply(new BigDecimal(quantity));
	}
}
