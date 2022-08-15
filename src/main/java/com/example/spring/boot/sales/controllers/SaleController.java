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
import com.example.spring.boot.sales.entities.Sale;
import com.example.spring.boot.sales.services.SaleService;

@RestController
@RequestMapping("/sale")
public class SaleController {
	
	@Autowired
	private SaleService saleService;
	
	@GetMapping
	public ResponseEntity<ResponseDTO> getAll() {
		try {
			return new ResponseEntity<>(new ResponseDTO(saleService.findAll()), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> getById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(new ResponseDTO(saleService.findById(id)), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<ResponseDTO> post(@RequestBody @Valid Sale sale) {
		try {
			return new ResponseEntity<>(new ResponseDTO(saleService.insert(sale)), HttpStatus.CREATED); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	public ResponseEntity<ResponseDTO> put(@RequestBody @Valid Sale sale) {
		try {
			return new ResponseEntity<>(new ResponseDTO(saleService.update(sale)), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable Long id) {
		try {
			saleService.delete(id);
			return new ResponseEntity<>(new ResponseDTO(), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
