package com.everis.restcontroller;

import com.everis.interfaces.Iparents;
import com.everis.model.Parents;
import com.everis.repository.ReactiveRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Parents/v1.0")
public class RestControllerParents implements Iparents {

  @Autowired
  private ReactiveRepository repository;
  
  @Override
  @GetMapping("/names/{fullName}")
  //@RequestMapping(value = "/names/{fullName}", method = RequestMethod.GET)
//  public Flux<ResponseEntity<Parents>> searchbyName(String fullName) {
//    return repository.findByFullName(fullName)
//    .map(people -> new ResponseEntity<>(people,HttpStatus.OK))
//    .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//  }
  public Flux<Parents> searchbyName(String fullName) {
    return repository.findByFullName(fullName);
  }

  @Override
  @GetMapping("/documents/{document}")
  public Mono<ResponseEntity<Parents>> searchbyDocument(String document) {
    return repository.findByIdentificationDocumentNumber(document)
   .map(people -> new ResponseEntity<>(people, HttpStatus.OK))
   .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @Override
  @GetMapping("/dates/{from}/{to}")
  public Flux<ResponseEntity<Parents>> searchbyrankdateofBirth(Date from, Date to) {
    return repository.findByDateofBirthBetween(from, to)
    .map(people -> new ResponseEntity<>(people, HttpStatus.OK))
    .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

  }

  @Override
  @PostMapping("/")
  public Mono<ResponseEntity<Void>> createParents(Parents parents) {
    return repository.save(parents)
    .then(Mono.just(new ResponseEntity<Void>(HttpStatus.CREATED)))
    .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @Override
  @GetMapping("/")
  public Flux<Parents> allParents() {
    return repository.findAll();
  }

  @Override
  @PutMapping("/{id}")
  public Mono<ResponseEntity<Parents>> modifyParents(String id, Parents parents) {
    return repository.findById(id)
  .flatMap(people -> {
    people.setId(id);
    people.setFullName(parents.getFullName());
    people.setGender(parents.getGender());
    people.setDateofBirth(parents.getDateofBirth());
    people.setTypeofIdentificationDocument(parents.getTypeofIdentificationDocument());
    people.setIdentificationDocumentNumber(parents.getIdentificationDocumentNumber());
    return repository.save(people);
  })
  .map(update -> new ResponseEntity<>(update, HttpStatus.OK))
  .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    
  }

  @Override
  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> deleteParents(String id) {
    return repository.findById(id)
  .flatMap(people ->
  repository.delete(people)
  .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))  
  )
  .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
