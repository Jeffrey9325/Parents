package com.everis.service;

import com.everis.model.Parents;
import com.everis.repository.ReactiveRepository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ParentsServiceImpl implements IParentsService {

  @Autowired
  ReactiveRepository repository;

  @Override
  public Flux<Parents> searchbyName(String name) {
    return repository.findByFullName(name);
  }

  @Override
  public Mono<Parents> searchbyDocument(String document) {
    return repository.findByIdentificationDocumentNumber(document);
  }

  @Override
  public Flux<Parents> searchbyrankdateofBirth(Date from, Date to) {
    return repository.findByDateofBirthBetween(from, to);
  }

  @Override
  public Mono<Parents> createParents(Parents parents) {
    return repository.save(parents);
  }

  @Override
  public Flux<Parents> allParents() {
    return repository.findAll();
  }

  @Override
  public Mono<Parents> modifyParents(Parents parents) {
    return repository.save(parents);
  }

  @Override
  public Mono<Void> deleteParents(Parents parents) {
    return repository.delete(parents);
  }

  @Override
  public Mono<Parents> findbyId(String id) {
    return repository.findById(id);
  }
}
