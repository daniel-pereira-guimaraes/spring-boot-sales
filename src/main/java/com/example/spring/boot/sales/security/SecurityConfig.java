package com.example.spring.boot.sales.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.spring.boot.sales.Messages;
import com.example.spring.boot.sales.dto.LoginDTO;
import com.example.spring.boot.sales.entities.User;
import com.example.spring.boot.sales.services.UserService;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailService userDetailService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors().and() // For external accesss!
			.csrf().disable() // Development time only!
			.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers(HttpMethod.POST, "/user").permitAll() // Permit create user!
				.anyRequest().authenticated()
			.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	public OncePerRequestFilter jwtFilter() {
		return new JwtAuthFilter(jwtService, userDetailService);
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService)
			.passwordEncoder(passwordEncoder());
	}
	
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public void verifyCredentials(LoginDTO loginDTO) throws Exception {
		User user = userService.findByUsername(loginDTO.getUsername());
		if (!passwordEncoder().matches(loginDTO.getPassword(), user.getPassword()))
			throw new Exception(Messages.get("login.invalid"));
	}
		
}
