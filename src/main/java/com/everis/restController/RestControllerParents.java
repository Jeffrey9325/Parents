package com.everis.restcontroller;

import com.everis.interfaces.Iparents;
import com.everis.model.Parents;
import com.everis.repository.ReactiveRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
  @RequestMapping(value = "/names/{fullName}", method = RequestMethod.GET)
  public Flux<Parents> searchbyName(String fullName) {
    return repository.findByFullName(fullName);
  }

  @Override
  @RequestMapping(value = "/documents/{document}", method = RequestMethod.GET)
  public Mono<Parents> searchbyDocument(String document) {
    return repository.findByIdentificationDocumentNumber(document);
  }

  @Override
  @RequestMapping(value = "/dates/{from}/{to}", method = RequestMethod.GET)
  public Flux<Parents> searchbyrankdateofBirth(Date from, Date to) {
    return repository.findByDateofBirthBetween(from, to);

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
  @PutMapping("{id}")
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
  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<ResponseEntity<Void>> deleteParents(String id) {
    return repository.findById(id)
.flatMap(people ->
repository.delete(people)
.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))  
)
.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
