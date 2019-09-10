package com.everis.repository;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.everis.model.Parents;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveRepository extends ReactiveMongoRepository<Parents, Serializable> {
	
	@Query("{ 'Nombrecompleto': ?0 }")
	Flux<Parents> findByNombrecompleto(String Nombrecompleto);
	
	@Query("{ 'Numerodocumentoidentificacion': ?0 }")
	Mono<Parents> findByNumerodocumentoidentificación(String numerodocumentoidentificación);
	
	@Query("select t from Students t where t. = ?1")
	Flux<Parents> findByrankDate(Date date1, Date date2 );
	
	Mono<Parents> findById(String id);

}
