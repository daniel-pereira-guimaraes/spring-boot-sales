package com.example.spring.boot.sales.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_user") // Prefixo tb_ pra evitar conflito com palavra reservada "user" do H2 Database.
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50, nullable = false, unique = true)
	@NotBlank(message = "{user.login.name.required}")
	@Size(min = 2, max = 50, message = "{user.login.name.min.max}")
	private String loginName;
	
	@Column(length = 64, nullable = false)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@Column(nullable = false)
	private Boolean isEnabled = false;

}
