package com.everis.restController;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.everis.interfaz.Iparents;
import com.everis.model.Parents;

import com.everis.repository.ReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Students/v1.0")
public class RestControllerParents implements Iparents {

	@Autowired
	private ReactiveRepository repository;

	@Override
	@RequestMapping(value = "/names/{name}", method = RequestMethod.GET)
	public Flux<Parents> searchbyName(String name) {
		return repository.findByNombrecompleto(name);	
	}
	
	@Override
	@RequestMapping(value = "/documents/{document}", method = RequestMethod.GET)
	public Mono<Parents> searchbyDocument(String document) {
		return repository.findByNumerodocumentoidentificación(document);
	}
	
	@Override
	@RequestMapping(value = "/dates/{date}", method = RequestMethod.GET)
	public Flux<Parents> searchbyrankDate(Date date1, Date date2) {
		return repository.findByrankDate(date1, date2);
	}

	@Override
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Mono<Parents> createStudent(Parents student) {
		return repository.save(student);
	}

	@Override
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Flux<Parents> allStudents() {
		return repository.findAll();
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Mono<Parents> modifyStudent(String id, Parents student) {
		//Students st = repository.findById(id);
		//student.setId(id);
		return repository.save(student);	
	}

	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> deleteStudents(String id) {
		
		return repository.deleteById(id);
	}
}
