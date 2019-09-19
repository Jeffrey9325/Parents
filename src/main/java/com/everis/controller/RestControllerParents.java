package com.everis.controller;

import com.everis.model.Parents;
import com.everis.service.ParentsServiceImpl;

import java.util.Date;

import javax.validation.Valid;

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

/**
 * RestControllerParents class.
 * @author jeffrey
 * @version v1.0
 */
@RestController
@RequestMapping("/Parents/v1.0")
public class RestControllerParents {

	//private static final Logger log = LoggerFactory.getLogger(ParentRestController.class);
 
  @Autowired
  ParentsServiceImpl repository;
  /**
   * search by fullname in Parents document.
   * @param fullName family name
   * @return
   */
  
  @GetMapping("/names/{fullName}")
  public Flux<Parents> searchbyName(@PathVariable final String fullName) { 
    return repository.searchbyName(fullName);
  }
  /**
   * search by identification document number in Parents document.
   * @param document identification document number
   * @return
   */
  
  @GetMapping("/documents/{document}")
  public Mono<Parents> searchbyDocument(@PathVariable final String document) {
    return repository.searchbyDocument(document);
  }
  
  /**
   * search by rank date of Birth in Parents document.
   * @param fromDate date
   * @param toDate date
   * @return
   */
  
  @GetMapping("/dates/{from}/{to}")
  public Flux<Parents> searchbyrankdateofBirth(
      @PathVariable @DateTimeFormat(iso = ISO.DATE) final Date fromDate,
      @PathVariable  @DateTimeFormat(iso = ISO.DATE) final Date toDate) {
    return repository.searchbyrankdateofBirth(fromDate, toDate);
  }
  /**
   * create record in Parents document.
   * @param parents people
   * @return
   */
  
  @PostMapping("/")
  public Mono<ResponseEntity<Parents>> createParents(@Valid @RequestBody final Parents parents) {
    return repository.createParents(parents)
  .then(Mono.just(new ResponseEntity<Parents>(HttpStatus.CREATED)))
  .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
  
//  @PostMapping("/")
//  public Mono<Parents> createParents(@Valid @RequestBody Parents parents) {
//    return repository.createParents(parents)
//  //.then(Mono.just( <Parents>(HttpStatus.CREATED)))
//  .defaultIfEmpty(Mono.error(new ApiRequestException("Error")));
//  }
  /**
   * show all record of Parents document.
   * @return
   */
  
  @GetMapping("/")
  public Flux<Parents> allParents() {
	 //7 throw new ApiRequestException("Error");
   return repository.allParents();
  }
  /**
   * modify record of Parents document.
   * @param idParents id
   * @param parents people
   * @return
   */
  
  @PutMapping("/{id}")
  public Mono<ResponseEntity<Parents>> modifyParents(@PathVariable final String idParents,
      @RequestBody final Parents parents) {
    return repository.findbyId(idParents)
  .flatMap(people -> {
    people.setId(idParents);
    people.setFullName(parents.getFullName());
    people.setGender(parents.getGender());
    people.setDateofBirth(parents.getDateofBirth());
    people.setTypeofIdentificationDocument(parents.getTypeofIdentificationDocument());
    people.setIdentificationDocumentNumber(parents.getIdentificationDocumentNumber());
    return repository.modifyParents(people);
  })
  .map(update -> new ResponseEntity<>(update, HttpStatus.OK))
  .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
  /**
   * delete record in Parents document.
   * @param idParents id
   * @return
   */
  
  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> deleteParents(@PathVariable final String idParents) {
    return repository.findbyId(idParents)
  .flatMap(people ->
  repository.deleteParents(people)
  .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))  
  )
  .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
