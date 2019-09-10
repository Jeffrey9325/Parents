package com.everis.interfaz;

import java.util.Date;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.everis.model.Parents;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Iparents {
	
	Flux<Parents> searchbyName(@PathVariable String name);
	
	Mono<Parents> searchbyDocument(@PathVariable String document);
	
	Flux<Parents> searchbyrankDate(@PathVariable Date date1,@PathVariable Date date2);
	
	Mono<Parents> createStudent(@RequestBody Parents student);
	
	Flux<Parents> allStudents();
	
	Mono<Parents> modifyStudent(@PathVariable String id, @RequestBody Parents student);
	
	Mono<Void> deleteStudents(@PathVariable String id);
}