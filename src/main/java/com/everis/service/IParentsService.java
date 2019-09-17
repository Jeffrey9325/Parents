package com.everis.service;

import com.everis.model.Parents;

import java.util.Date;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IParentsService {

  Flux<Parents> searchbyName(String name);

  Mono<Parents> searchbyDocument(String document);

  Flux<Parents> searchbyrankdateofBirth(Date from, Date to);

  Mono<Parents> createParents(Parents parents);

  Flux<Parents> allStudents();

  Mono<Parents> modifyParents(Parents parents);

  Mono<Void> deleteParents(Parents parents);
  
  Mono<Parents> findbyId(String id);
  
}