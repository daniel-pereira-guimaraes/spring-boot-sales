package com.example.spring.boot.sales.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.boot.sales.dto.LoginDTO;
import com.example.spring.boot.sales.dto.ResponseDTO;
import com.example.spring.boot.sales.dto.TokenDTO;
import com.example.spring.boot.sales.security.JwtService;
import com.example.spring.boot.sales.security.SecurityConfig;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private SecurityConfig securityConfig;
	
	@PostMapping
	public ResponseEntity<ResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
		try {
			securityConfig.verifyCredentials(loginDTO);
			TokenDTO tokenDTO = jwtService.generateToken(loginDTO.getUsername());
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(tokenDTO), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(e.getMessage()), HttpStatus.UNAUTHORIZED);
		}
	}

	
}
