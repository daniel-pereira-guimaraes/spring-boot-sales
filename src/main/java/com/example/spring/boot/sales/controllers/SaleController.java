package com.example.spring.boot.sales.controllers;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.example.spring.boot.sales.dto.SaleFilterDTO;
import com.example.spring.boot.sales.entities.Sale;
import com.example.spring.boot.sales.entities.SaleItem;
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
	
	@GetMapping("/filter")
	public ResponseEntity<ResponseDTO> getByFilter(@RequestBody SaleFilterDTO filter) {
		try {
			return new ResponseEntity<>(new ResponseDTO(saleService.findByFilter(filter)), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/summary/date/{startDate}/{endDate}")
	public ResponseEntity<ResponseDTO> getSummaryByDate(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, 
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
		try {
			return new ResponseEntity<>(new ResponseDTO(saleService.summaryByDate(startDate, endDate)), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/summary/date")
	public ResponseEntity<ResponseDTO> getSummaryByDate() {
		try {
			return new ResponseEntity<>(new ResponseDTO(saleService.summaryByDate(null, null)), HttpStatus.OK);
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
	
	/* ITEMS */
	
	@GetMapping("/items/{saleId}")
	public ResponseEntity<ResponseDTO> getItemBySaleId(@PathVariable Long saleId) {
		try {
			return new ResponseEntity<>(new ResponseDTO(saleService.findItemBySaleId(saleId)), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/item/{id}")
	public ResponseEntity<ResponseDTO> getItemById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(new ResponseDTO(saleService.findItemById(id)), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@PostMapping("/item")
	public ResponseEntity<ResponseDTO> postItem(@RequestBody @Valid SaleItem item) {
		try {
			return new ResponseEntity<>(new ResponseDTO(saleService.insertItem(item)), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/item")
	public ResponseEntity<ResponseDTO> putItem(@RequestBody @Valid SaleItem item) {
		try {
			return new ResponseEntity<>(new ResponseDTO(saleService.updateItem(item)), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/item/{id}")
	public ResponseEntity<ResponseDTO> deleteItem(@PathVariable Long id) {
		try {
			saleService.deleteItem(id);
			return new ResponseEntity<>(new ResponseDTO(), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
