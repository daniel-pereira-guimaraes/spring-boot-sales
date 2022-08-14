package com.example.spring.boot.sales.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.boot.sales.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public Optional<User> findByLoginName(String loginName);

}
