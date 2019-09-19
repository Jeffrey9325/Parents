package com.everis.repository;

import com.everis.model.Parents;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveRepository extends ReactiveMongoRepository<Parents, Serializable> {

  Flux<Parents> findByFullName(String fullName);

  Mono<Parents> findByIdentificationDocumentNumber(String identificationDocumentNumber);
  
  Flux<Parents> findByDateofBirthBetween(Date from, Date to);

  Mono<Parents> findById(String id);

}
