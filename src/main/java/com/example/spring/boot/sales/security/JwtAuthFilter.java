package com.example.spring.boot.sales.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.spring.boot.sales.dto.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthFilter extends OncePerRequestFilter {
	
	private JwtService jwtService;
	private CustomUserDetailService userDetailService;
	
	public JwtAuthFilter(JwtService jwtService, CustomUserDetailService userDetailService) {
		super();
		this.jwtService = jwtService;
		this.userDetailService = userDetailService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		try {
			String authorization = request.getHeader("Authorization");
			if (authorization != null) {
			
				String[] parts = authorization.split(" ");
				if (parts.length == 2 && parts[0].equals("Bearer")) {
	
					String token = parts[1];
					String username = jwtService.getUsernameFromToken(token);
				
					// Load user details
					UserDetails userDetails = userDetailService.loadUserByUsername(username);
					
					// Create a authentication for spring security context.
					UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
					
					// Configure authentication as web authenticate.
					upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	
					// Add authentication on spring security context.
					SecurityContextHolder.getContext().setAuthentication(upat);
				}
			}
			
			filterChain.doFilter(request, response);
			
		} catch(Exception e) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.getWriter().write(toJson(new ResponseDTO(e.getMessage())));
		}
	}

	private String toJson(Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);
	}
	
}
