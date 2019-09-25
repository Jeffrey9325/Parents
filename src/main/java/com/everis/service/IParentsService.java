package com.everis.service;

import com.everis.model.Parents;

import java.util.Date;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * IParentsService interface.
 * @author jeffrey
 * @version v1.0
 */

public interface IParentsService {
  /**
   * search by name parents document.
   * @param name full name 
   * @return
   */

  Flux<Parents> searchbyName(String name);
  /**
   * search by document parents document.
   * @param document identification document number
   * @return
   */
  
  Mono<Parents> searchbyDocument(String document);
  /**
   * search by rank date of birth.
   * @param fromDate date
   * @param toDate date
   * @return
   */
  
  Flux<Parents> searchbyrankdateofBirth(Date fromDate, Date toDate);
  /**
   * create record parents document.
   * @param parents parents
   * @return
   */
  
  Mono<Parents> createParents(Parents parents);
  /**
   * show all parents of parents document.
   * @return
   */
  
  Flux<Parents> allParents();
  /**
   * modify record parents document.
   * @param parents parents
   * @return
   */
  
  Mono<Parents> modifyParents(Parents parents);
  /**
   * delete record of parents document.
   * @param parents parents
   * @return
   */
  
  Mono<Void> deleteParents(Parents parents);
  /**
   * find by id parents document.
   * @param idParents identification
   * @return
   */
  
  Mono<Parents> findbyId(String idParents);
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