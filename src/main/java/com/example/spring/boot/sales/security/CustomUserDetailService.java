package com.example.spring.boot.sales.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.spring.boot.sales.services.UserService;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			return new CustomUserDetails(userService.findByUsername(username));
		} catch (Exception e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

}
