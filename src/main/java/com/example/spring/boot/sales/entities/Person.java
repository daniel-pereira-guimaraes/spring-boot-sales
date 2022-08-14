package com.example.spring.boot.sales.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 70, nullable = false)
	@NotBlank(message = "{person.name.required}")
	@Size(min = 2, max = 70, message = "{person.name.min.max}")
	private String name;
	
	@Column(length = 20, nullable = false, unique = true)
	@Size(max = 20, message = "{person.tax.id.max}")
	private String taxId;

}
