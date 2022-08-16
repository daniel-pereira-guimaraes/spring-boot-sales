package com.example.spring.boot.sales.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class SaleItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore 
	private Sale sale;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "{sale.item.product.required}")
	private Product product;
	
	@Column(nullable = false)
	@NotNull(message = "{sale.item.quantity.required}")
	@Min(value = 1, message = "{sale.item.quantity.min.max}")
	@Max(value = 9999999, message = "{sale.item.quantity.min.max}")
	private Long quantity;

	@Column(nullable = false, precision = 18, scale = 8)
	private BigDecimal costPrice;
	
	@Column(nullable = false, precision = 18, scale = 2)
	@DecimalMin(value = "0.01", message = "{sale.item.price.min.max}")
	@DecimalMax(value = "999999999.99", message = "{sale.item.price.min.max}")
	@NotNull(message = "{sale.item.price.required}")
	private BigDecimal salePrice;

}
