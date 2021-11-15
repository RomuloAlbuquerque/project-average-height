
package com.project.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.dto.PersonDTO;
import com.project.services.PersonService;

@RestController
@RequestMapping(value = "/people")
public class PersonResource {
	
	@Autowired
	private PersonService service;
	
	@PostMapping
	public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO dto){
		
		dto = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
	
	@GetMapping(value = "/average-height")
	public double averageHeight() {
		
		double averageHeight = service.averageHeight();
		
		return averageHeight;
	}
	
	@GetMapping(value = "/greater-height")
	public List<PersonDTO> greaterHeight() {
		List<PersonDTO> list = service.greaterHeight();
		
		return list;
	}
	
	@GetMapping(value = "/shorter-height")
	public List<PersonDTO> shorterHeight() {
		List<PersonDTO> list = service.shorterHeight();
		
		return list;
	}
	
	@GetMapping(value = "/amount-below-average-height")
	public int amountBelowAverageHeight() {
		int amount = service.amountBelowAverageHeight();
		
		return amount;
	}
	
	@GetMapping(value = "/people-with-below-average-height")
	public List<PersonDTO> peopleWithBelowAverageHeight(){
		List<PersonDTO> listDTO = service.peopleWithBelowAverageHeight();
		
		return listDTO;
	}

}
