package com.everis.service;

import com.everis.model.Parents;
import com.everis.repository.ReactiveRepository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * ParentsServiceImpl class.
 * @author jeffrey
 * @version v1.0
 */

@Service
public class ParentsServiceImpl implements IParentsService {
  /**
   * Reactive Repository.
   */
  @Autowired
  private ReactiveRepository repository;

  @Override
  public Flux<Parents> searchbyName(final String name) {
    return repository.findByFullName(name);
  }

  @Override
  public Mono<Parents> searchbyDocument(final String documentNumber) {
    return repository.findByDocumentNumber(documentNumber);
  }

  @Override
  public Flux<Parents> searchbyrankdateofBirth(final Date from, final Date to) {
    return repository.findByDateofBirthBetween(from, to);
  }

  @Override
  public Mono<Parents> createParents(final Parents parents) {
    return repository.save(parents);
  }

  @Override
  public Flux<Parents> allParents() {
    return repository.findAll();
  }

  @Override
  public Mono<Parents> modifyParents(final Parents parents) {
    return repository.save(parents);
  }

  @Override
  public Mono<Void> deleteParents(final Parents parents) {
    return repository.delete(parents);
  }

  @Override
  public Mono<Parents> findbyId(final String idParents) {
    return repository.findById(idParents);
  }

  @Override
  public Flux<Parents> findByIdStudent(String idStudent) {
    return repository.findByIdStudent(idStudent);
  }

  @Override
  public Flux<Parents> findByIdFamily(String idFamily) {
    return repository.findByIdFamily(idFamily);
  }
}
