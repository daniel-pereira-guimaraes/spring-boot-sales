package com.example.spring.boot.sales.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.boot.sales.Messages;
import com.example.spring.boot.sales.entities.User;
import com.example.spring.boot.sales.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(Long id) throws Exception {
		return userRepository.findById(id)
			.orElseThrow(() -> new Exception(Messages.format("user.not.found", id)));
	}
	
	public User findByUsername(String username) throws Exception {
		return userRepository.findByUsername(username)
			.orElseThrow(() -> new Exception(Messages.format("user.not.found", username)));
	}
	
	public User insert(User user) {
		user.setIsEnabled(true);
		return userRepository.save(user);
	}
	
	public User update(User user) {
		return userRepository.save(user);
	}

	public void delete(Long id) throws Exception {
		userRepository.delete(findById(id));
	}
	
}
