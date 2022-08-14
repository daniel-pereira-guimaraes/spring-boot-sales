package com.example.spring.boot.sales.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.boot.sales.Messages;
import com.example.spring.boot.sales.entities.Person;
import com.example.spring.boot.sales.repositories.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	public List<Person> findAll() {
		//return personRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		//return personRepository.findAll(Sort.by("name"));
		return personRepository.findAllByOrderByName();
	}
	
	public Person findById(Long id) throws Exception {
		return personRepository.findById(id)
			.orElseThrow(() -> new Exception(Messages.format("person.not.found", id)));
	}
	
	public Person findByTaxId(String taxId) throws Exception {
		return personRepository.findByTaxId(taxId)
			.orElseThrow(() -> new Exception(Messages.format("person.not.found", taxId)));
	}
	
	public Person insert(Person person) throws Exception {
		person.setName(person.getName().toUpperCase());
		String taxId = person.getTaxId();
		if (taxId != null && !taxId.isEmpty()) {
			Person p = personRepository.findByTaxId(taxId).get();
			if (p != null)
				throw new Exception(Messages.format("person.tax.id.duplicate", taxId));
		}
		return personRepository.save(person);
	}
	
	public Person update(Person person) throws Exception {
		person.setName(person.getName().toUpperCase());
		String taxId = person.getTaxId();
		if (taxId != null && !taxId.isEmpty()) {
			Person p = personRepository.findByTaxId(taxId).get();
			if (p != null && p.getId() != person.getId())
				throw new Exception(Messages.format("person.tax.id.duplicate", taxId));
		}
		return personRepository.save(person);
	}

	public void delete(Long id) throws Exception {
		personRepository.delete(findById(id));
	}
	
}
