package com.example.spring.boot.sales.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.spring.boot.sales.Messages;
import com.example.spring.boot.sales.dto.ResponseDTO;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		List<String> messages = new ArrayList<String>();
		e.getAllErrors().forEach(error -> {
			messages.add(error.getDefaultMessage());
		});
		return new ResponseDTO(messages);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseDTO handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		return new ResponseDTO(Messages.get("http.message.not.readable"));
	}

}

