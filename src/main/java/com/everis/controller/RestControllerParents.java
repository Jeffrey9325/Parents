package com.everis.controller;

import com.everis.model.Parents;
import com.everis.service.ParentsServiceImpl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Parents/v1.0")
public class RestControllerParents {

  @Autowired
  ParentsServiceImpl repository;

  @GetMapping("/names/{fullName}")
  public Flux<Parents> searchbyName(@PathVariable String fullName) {
    return repository.searchbyName(fullName);
  }

  @GetMapping("/documents/{document}")
  public Mono<Parents> searchbyDocument(@PathVariable String document) {
    return repository.searchbyDocument(document);
  }

  @GetMapping("/dates/{from}/{to}")
  public Flux<Parents> searchbyrankdateofBirth(@PathVariable @DateTimeFormat(iso = ISO.DATE) Date from, @PathVariable  @DateTimeFormat(iso = ISO.DATE)  Date to) {
	return repository.searchbyrankdateofBirth(from, to);
  }

  @PostMapping("/")
  public Mono<ResponseEntity<Parents>> createStudent(@RequestBody Parents parents) {
	  return repository.createParents(parents)
			    .then(Mono.just(new ResponseEntity<Parents>(HttpStatus.CREATED)))
			    .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping("/")
  public Flux<Parents> allStudents() {
    return repository.allStudents();
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<Parents>> modifyStudent(@PathVariable String id, @RequestBody Parents parents) {
    return repository.findbyId(id)
    		.flatMap(People -> {
    			People.setId(id);
    			People.setFullName(parents.getFullName());
    			People.setGender(parents.getGender());
    			People.setDateofBirth(parents.getDateofBirth());
    			People.setTypeofIdentificationDocument(parents.getTypeofIdentificationDocument());
    			People.setIdentificationDocumentNumber(parents.getIdentificationDocumentNumber());
    			return repository.modifyParents(People);
    		})
    		.map(update -> new ResponseEntity<>(update, HttpStatus.OK))
    		.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> deleteStudents(@PathVariable String id) {
	  return repository.findbyId(id)
			  .flatMap(people ->
				  repository.deleteParents(people)
				  		.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))  
			  )
			  .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
