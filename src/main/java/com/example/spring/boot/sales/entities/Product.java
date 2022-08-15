package com.example.spring.boot.sales.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 15, unique = true)
	private String gtin;
	
	@Column(length = 50, nullable = false)
	@NotBlank(message = "{product.description.required}")
	@Size(min = 3, message="{product.description.min.max}")
	private String description;
	
	@Column(nullable = false, updatable = false)
	@NotNull(message = "{product.quantity.required}")
	@Min(value = 0, message = "{product.quantity.min.max}")
	@Max(value = 9999999, message = "{product.quantity.min.max}")
	private Long quantity = 0L;
	
	@Column(precision = 18, scale = 8, nullable = false)
	private BigDecimal costPrice = new BigDecimal(0);

	@Column(precision = 18, scale = 2, nullable = false)
	@NotNull(message = "{product.sale.price.required}")
	@DecimalMin(value = "0.01", message = "{product.sale.price.min.max}")
	@DecimalMax(value = "999999999.99", message = "{product.sale.price.min.max}")
	private BigDecimal salePrice = new BigDecimal(0);

}
