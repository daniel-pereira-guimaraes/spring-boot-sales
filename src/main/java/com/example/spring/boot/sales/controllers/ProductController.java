package com.example.spring.boot.sales.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.boot.sales.dto.ResponseDTO;
import com.example.spring.boot.sales.entities.Product;
import com.example.spring.boot.sales.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<ResponseDTO> getAll() {
		try {
			return new ResponseEntity<>(new ResponseDTO(productService.findAll()), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> getById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(new ResponseDTO(productService.findById(id)), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping("/gtin/{gtin}")
	public ResponseEntity<ResponseDTO> getById(@PathVariable String gtin) {
		try {
			return new ResponseEntity<>(new ResponseDTO(productService.findByGtin(gtin)), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("inc/quantity/{id}/{increment}")
	public ResponseEntity<ResponseDTO> incQuantity(
		@PathVariable Long id, @PathVariable Long increment) {
		try {
			return new ResponseEntity<>(new ResponseDTO(productService.incQuantity(id, increment)), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<ResponseDTO> post(@RequestBody @Valid Product product) {
		try {
			return new ResponseEntity<>(new ResponseDTO(productService.insert(product)), HttpStatus.CREATED); 
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	public ResponseEntity<ResponseDTO> put(@RequestBody @Valid Product product) {
		try {
			return new ResponseEntity<>(new ResponseDTO(productService.update(product)), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable Long id) {
		try {
			productService.delete(id);
			return new ResponseEntity<>(new ResponseDTO(), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
