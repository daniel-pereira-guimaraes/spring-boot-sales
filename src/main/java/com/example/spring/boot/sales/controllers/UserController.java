package com.example.spring.boot.sales.controllers;

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
import com.example.spring.boot.sales.entities.User;
import com.example.spring.boot.sales.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<ResponseDTO> getAll() {
		try {
			return new ResponseEntity<>(new ResponseDTO(userService.findAll()), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{param}")
	public ResponseEntity<ResponseDTO> getById(@PathVariable String param) {
		try {
			User user = param.matches("\\d*") ?
				userService.findById(Long.parseLong(param)) :
				userService.findByLoginName(param);
			return new ResponseEntity<>(new ResponseDTO(user), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<ResponseDTO> post(@RequestBody User user) {
		try {
			return new ResponseEntity<>(new ResponseDTO(userService.insert(user)), HttpStatus.CREATED); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	public ResponseEntity<ResponseDTO> put(@RequestBody User user) {
		try {
			return new ResponseEntity<>(new ResponseDTO(userService.update(user)), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable Long id) {
		try {
			userService.delete(id);
			return new ResponseEntity<>(new ResponseDTO(), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
