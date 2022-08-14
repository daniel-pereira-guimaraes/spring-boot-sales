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
import com.example.spring.boot.sales.entities.Person;
import com.example.spring.boot.sales.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping
	public ResponseEntity<ResponseDTO> getAll() {
		try {
			return new ResponseEntity<>(new ResponseDTO(personService.findAll()), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> getById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(new ResponseDTO(personService.findById(id)), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping("/taxId") // For security, taxId was passed in the request body.
	public ResponseEntity<ResponseDTO> getById(@RequestBody String taxId) {
		try {
			return new ResponseEntity<>(new ResponseDTO(personService.findByTaxId(taxId)), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<ResponseDTO> post(@RequestBody @Valid Person person) {
		try {
			return new ResponseEntity<>(new ResponseDTO(personService.insert(person)), HttpStatus.CREATED); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	public ResponseEntity<ResponseDTO> put(@RequestBody @Valid Person person) {
		try {
			return new ResponseEntity<>(new ResponseDTO(personService.update(person)), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable Long id) {
		try {
			personService.delete(id);
			return new ResponseEntity<>(new ResponseDTO(), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
