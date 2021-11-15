package com.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dto.PersonDTO;
import com.project.entities.Person;
import com.project.repositories.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository repository;
	
	@Transactional
	public PersonDTO create(PersonDTO dto) {
		
		Person entity = new Person();
		entity.setName(dto.getName());
		entity.setHeight(dto.getHeight());
		entity = repository.save(entity);
		
		return new PersonDTO(entity);
	}
	
	@Transactional
	public Double averageHeight() {
		List<Person> list = new ArrayList<>();
		list = repository.findAll();

		double sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).getHeight();
		}
		double averageHeight = sum / list.size();

		return averageHeight;
	}

}
