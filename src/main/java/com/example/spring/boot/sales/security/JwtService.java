package com.example.spring.boot.sales.security;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.spring.boot.sales.dto.TokenDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Value("${security.jwt.expiration:60}")
	private int expiration;
	
	@Value("${security.jwt.key}")
	private String key;
	
	public TokenDTO generateToken(String username) {
		Date expirationDate = new Date(new Date().getTime() + 1000 * 60 * expiration);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

		TokenDTO tokenDTO = new TokenDTO();
		tokenDTO.setExpiration(sdf.format(expirationDate));
		tokenDTO.setToken(Jwts.builder()
			.setSubject(username)
			.setExpiration(expirationDate)
			.signWith(getSecretKey())
			.compact());
		
		return tokenDTO;
	}

	private SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
	}
	
	private Claims getClaims(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(getSecretKey())
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	public String getUsernameFromToken(String token) {
		return getClaims(token).getSubject();
	}

}
