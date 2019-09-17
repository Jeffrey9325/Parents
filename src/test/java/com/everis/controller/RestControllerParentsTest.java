package com.everis.controller;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.everis.model.Parents;
import com.everis.repository.ReactiveRepository;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestControllerParentsTest {

	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	ReactiveRepository repository;
	
	@Test
	public void searchbyName() {
		 Parents show = repository.findByFullName("jeffrey").blockFirst();
		 webTestClient.get()
	                .uri("/Parents/v1.0/names/{fullName}", Collections.singletonMap("fullName", show.getFullName()))
	                .accept(MediaType.APPLICATION_JSON_UTF8)
	                .exchange()
	                .expectStatus().isOk()
	                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
	                .expectBodyList(Parents.class)
	                .consumeWith(response -> {
	                	List<Parents> parents = response.getResponseBody();
	                	parents.forEach(p ->{
	        				System.out.println(p.getId());
	        				System.out.println(p.getFullName());
	        				System.out.println(p.getGender());
	        				System.out.println(p.getDateofBirth());
	        				System.out.println(p.getTypeofIdentificationDocument());
	        				System.out.println(p.getIdentificationDocumentNumber());
	        				
	        			});
	                
//	                    Students show = response.getResponseBody();
//	                    Assertions.assertThat(show.getFullName()).isNotEmpty();
//	                    Assertions.assertThat(show.getFullName().length()>0).isTrue();
//
	                });
	}
	
	@Test
	public void searchbyDocument() {
		
		Parents parents = repository.findByIdentificationDocumentNumber("47704995").block();
		webTestClient.get()
                .uri("/Parents/v1.0/documents/{document}", Collections.singletonMap("document", parents.getIdentificationDocumentNumber()))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Parents.class)
                .consumeWith(response -> {
                	Parents show = response.getResponseBody();
                    Assertions.assertThat(show.getIdentificationDocumentNumber()).isNotEmpty();
                    Assertions.assertThat(show.getIdentificationDocumentNumber().length()>0).isTrue();

                });
	
	}
	
//	@Test
//	public void searchbyrankdateofBirth() throws ParseException {
//		
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date fromDate = sdf.parse("2016-02-17");
//		
//		Date toDate = sdf.parse("2019-09-17");
//		
//		Parents parents1 = repository.findById("5d7bcb121517622ed0112798").block();
//		
//		Parents parents2 = repository.findById("5d81187c36789644980d34c7").block();
//		
//		webTestClient.get()
//                .uri("/Parents/v1.0/dates/{from}/{to}", Collections.singletonMap("from", parents1.getDateofBirth()),Collections.singletonMap("to", parents2.getDateofBirth()))
//                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .exchange()
//                .expectStatus().isOk()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
//                .expectBodyList(Parents.class)
//                .consumeWith(response -> {
//                	List<Parents> parents = response.getResponseBody();
//        			parents.forEach(p ->{
//        				System.out.println(p.getId());
//        				System.out.println(p.getFullName());
//        				System.out.println(p.getGender());
//        				System.out.println(p.getDateofBirth());
//        				System.out.println(p.getTypeofIdentificationDocument());
//        				System.out.println(p.getIdentificationDocumentNumber());
//        				
//        			});
//
//                });
//	
//	}
	
	@Test
	public void createParents() throws java.text.ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = sdf.parse("2019-09-16");
		Parents parent = new Parents("1", "richard", "m", fecha, "dni", "4770888");
		
		webTestClient.post()
		.uri("/Parents/v1.0/")
		.contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .body(Mono.just(parent), Parents.class)
		.exchange()
		.expectStatus().isCreated();
	}
	
	@Test
	public void allParents() {
		
		webTestClient.get()
		.uri("/Parents/v1.0/")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBodyList(Parents.class)
		.consumeWith(response -> {
			List<Parents> parents = response.getResponseBody();
			parents.forEach(p ->{
				System.out.println(p.getId());
				System.out.println(p.getFullName());
				System.out.println(p.getGender());
				System.out.println(p.getDateofBirth());
				System.out.println(p.getTypeofIdentificationDocument());
				System.out.println(p.getIdentificationDocumentNumber());
				
			});
			
			//Assertions.assertThat(student.size()==6)
		});
		
	}
	
	@Test
	public void testUpdateStudent() throws ParseException {
		
		Parents parents = repository.findById("1").block();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = sdf.parse("2019-09-16");
		Parents newParents = new Parents(parents.getId(), "Jeff", "m", fecha, "dni", "159748");

        webTestClient.put()
        .uri("/Parents/v1.0/{id}", Collections.singletonMap("id", parents.getId()))
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .body(Mono.just(newParents), Parents.class)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBody()
        .jsonPath("$.id").isNotEmpty()
        .jsonPath("$.id").isEqualTo("1");
	}
	
	@Test
    public void deleteStudents(){
	
		Parents parents = repository.findById("1").block();

	    webTestClient.delete()
                .uri("/Parents/v1.0/{id}", Collections.singletonMap("id",  parents.getId()))
                .exchange()
                .expectStatus().isNoContent()
                .expectBody()
                .isEmpty();
    }
	
}
