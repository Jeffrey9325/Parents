package com.everis.repository;

import com.everis.model.Parents;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * ReactiveRepository interface.
 * @author jeffrey
 * @version v1.0
 */

@Repository
public interface ReactiveRepository extends ReactiveMongoRepository<Parents, Serializable> {
  /**
   * find by Full Name parents document.
   * @param fullName full name
   * @return
   */
  Flux<Parents> findByFullName(String fullName);
  /**
   * find by identification document number parents document.
   * @param documentNumber identification document number
   * @return
   */
  
  Mono<Parents> findByDocumentNumber(String documentNumber);
  /**
   * find by rank date of birth parents document.
   * @param fromDate date
   * @param toDate date
   * @return
   */
  
  Flux<Parents> findByDateofBirthBetween(Date fromDate, Date toDate);
  /**
   * find by id parents document.
   * @param idParents id
   * @return
   */
  
  Mono<Parents> findById(String idParents);
  /**
   * find by id student parents document. 
   * @param idStudent identification
   * @return
   */
  
  Flux<Parents> findByIdStudent(String idStudent);
  /**
   * find by id family parents document.
   * @param idFamily identification
   * @return
   */
  
  Flux<Parents> findByIdFamily(String idFamily);

}
