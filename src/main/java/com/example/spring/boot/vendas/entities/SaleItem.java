package com.example.spring.boot.vendas.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SaleItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Sale sale;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@NotNull(message = "{sale.item.product.required}")
	private Product product;
	
	@Column(nullable = false)
	@NotNull(message = "{sale.item.quantity.required}")
	@Size(min = 1, max = 9999999, message = "{sale.item.quantity.min.max}")
	private Long quantity;

	@Column(nullable = false, precision = 18, scale = 8)
	private BigDecimal costPrice;
	
	@Column(nullable = false, precision = 18, scale = 2)
	@DecimalMin(value = "0.01", message = "{sale.item.price.min.max}")
	@DecimalMax(value = "999999999.99", message = "{sale.item.price.min.max}")
	@NotNull(message = "{sale.item.price.required}")
	private BigDecimal salePrice;

}
