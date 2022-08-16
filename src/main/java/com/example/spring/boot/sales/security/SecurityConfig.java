package com.example.spring.boot.sales.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailService userDetailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors().and() // For external accesss!
			.csrf().disable() // Development time only!
			.authorizeRequests()
				.antMatchers("/login")
				.permitAll()
			.and()
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/user") // Permit create user!
				.permitAll()
			.anyRequest().authenticated()
			.and()
			.httpBasic();
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService)
			.passwordEncoder(passwordEncoder());
	}
	
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
