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
	
	@Transactional
	public List<PersonDTO> greaterHeight() {
		List<Person> list = new ArrayList<>();
		list = repository.findAll();

		double height = 0.0;
		Person entity = new Person();
		for (int i = 0; i < list.size(); i++) {
			double temp = list.get(i).getHeight();
			if (temp > height) {
				height = temp;
				entity = list.get(i);
			}
		}
		PersonDTO dto = new PersonDTO(entity);

		List<Person> listPeople = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			double temp = list.get(i).getHeight();
			if (temp == dto.getHeight()) {
				listPeople.add(list.get(i));
			}
		}
		
		List<PersonDTO> listDTO = new ArrayList<>();
		for(int i = 0; i < listPeople.size(); i++) {
			PersonDTO tempDTO = new PersonDTO(listPeople.get(i));
			listDTO.add(tempDTO);
		}

		return listDTO;
	}
	
	@Transactional
	public List<PersonDTO> shorterHeight() {
		List<Person> list = new ArrayList<>();
		list = repository.findAll();

		double height = 10.0;
		Person entity = new Person();
		for (int i = 0; i < list.size(); i++) {
			double temp = list.get(i).getHeight();
			if (temp < height) {
				height = temp;
				entity = list.get(i);
			}
		}
		PersonDTO dto = new PersonDTO(entity);

		List<Person> listPeople = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			double temp = list.get(i).getHeight();
			if (temp == dto.getHeight()) {
				listPeople.add(list.get(i));
			}
		}
		
		List<PersonDTO> listDTO = new ArrayList<>();
		for(int i = 0; i < listPeople.size(); i++) {
			PersonDTO tempDTO = new PersonDTO(listPeople.get(i));
			listDTO.add(tempDTO);
		}

		return listDTO;
	}
	
	@Transactional
	public int amountBelowAverageHeight() {
		List<Person> list = new ArrayList<>();
		list = repository.findAll();

		double sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).getHeight();
		}
		double media = sum / list.size();

		int amountBelowAverageheight = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getHeight() < media) {
				amountBelowAverageheight++;
			}
		}

		return amountBelowAverageheight;
	}
	
	@Transactional
	public List<PersonDTO> peopleWithBelowAverageHeight(){
		
		List<Person> list = new ArrayList<>();
		list = repository.findAll();

		double sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).getHeight();
		}
		double media = sum / list.size();
		Person entity = new Person();
		List<Person> listPeopleWithBelowAverageHeight = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			double temp = list.get(i).getHeight();
			if (temp < media) {
				entity = list.get(i);
				listPeopleWithBelowAverageHeight.add(entity);
			}
		}
		
		List<PersonDTO> listDTO = new ArrayList<>();
		for (int i = 0; i < listPeopleWithBelowAverageHeight.size(); i++) {
			PersonDTO dto = new PersonDTO(listPeopleWithBelowAverageHeight.get(i));
			listDTO.add(dto);
		}
		
		return listDTO;
	}

}
