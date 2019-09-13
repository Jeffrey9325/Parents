package com.everis.interfaces;

import com.everis.model.Parents;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Iparents {

  Flux<Parents> searchbyName(@PathVariable String fullName);

  Mono<Parents> searchbyDocument(@PathVariable String document);

  Flux<Parents> searchbyrankdateofBirth(@PathVariable @DateTimeFormat(iso = ISO.DATE) Date from,
      @PathVariable  @DateTimeFormat(iso = ISO.DATE) Date to);

  Mono<Parents> createParents(@RequestBody Parents parents);

  Flux<Parents> allParents();

  Mono<ResponseEntity<Parents>> modifyParents(@PathVariable String id,
      @RequestBody Parents student);

  Mono<ResponseEntity<Void>> deleteParents(@PathVariable String id);
  
}