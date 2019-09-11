package com.everis.interfaces;

import com.everis.model.Parents;

import java.util.Date;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Iparents {

  Flux<Parents> searchbyName(@PathVariable String fullName);

  Mono<Parents> searchbyDocument(@PathVariable String document);

  Flux<Parents> searchbyrankdateofBirth(@PathVariable Date from, @PathVariable Date to);

  Mono<Parents> createParents(@RequestBody Parents parents);

  Flux<Parents> allParents();

  Mono<Parents> modifyParents(@PathVariable String id, @RequestBody Parents parents);

  Mono<Void> deleteParents(@PathVariable String id);
  
}