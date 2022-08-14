package com.example.spring.boot.sales.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.boot.sales.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	public List<Person> findAllByOrderByName();

}
