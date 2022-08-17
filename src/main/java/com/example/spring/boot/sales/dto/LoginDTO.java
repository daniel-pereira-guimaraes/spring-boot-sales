package com.example.spring.boot.sales.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
	
	@NotBlank(message = "{user.username.required}")
	private String username;

	@NotBlank(message = "{user.password.required}")
	private String password;

}
