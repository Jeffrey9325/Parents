package com.everis.restcontroller;

import com.everis.interfaces.Iparents;
import com.everis.model.Parents;
import com.everis.repository.ReactiveRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  @RequestMapping(value = "/names/{fullName}", method = RequestMethod.GET)
  public Flux<Parents> searchbyName(String fullName) {
    return repository.findByfullName(fullName);
  }

  @Override
  @RequestMapping(value = "/documents/{document}", method = RequestMethod.GET)
  public Mono<Parents> searchbyDocument(String document) {
    return repository.findByidentificationDocumentNumber(document);
  }

  @Override
  @RequestMapping(value = "/dates/{date}", method = RequestMethod.GET)
  public Flux<Parents> searchbyrankdateofBirth(Date from, Date to) {
    //return repository.findByrankdateofBirth(date1, date2);
	  return null;
  }

  @Override
  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Parents> createParents(Parents parents) {
    return repository.save(parents);
  }

  @Override
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public Flux<Parents> allParents() {
    return repository.findAll();
  }

  @Override
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public Mono<Parents> modifyParents(String id, Parents parents) {
    //Mono<Parents> st = repository.findById(id);
    parents.setId(id);
    return repository.save(parents);
  }

  @Override
  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteParents(String id) {
    return repository.deleteById(id);
  }
}
