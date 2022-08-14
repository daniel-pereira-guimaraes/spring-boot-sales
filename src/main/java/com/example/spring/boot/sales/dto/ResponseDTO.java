package com.example.spring.boot.sales.dto;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {
	
	private Object data;
	private List<String> messages;
	
	public ResponseDTO(Object data) {
		this.data = data;
	}
	
	public ResponseDTO(String message) {
		this.messages = Arrays.asList(message);
	}
	
	public ResponseDTO(List<String> messages) {
		this.messages = messages;
	}

}
