package com.everis.controller;

import com.everis.model.Parents;
import com.everis.repository.ReactiveRepository;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;
/**
 * RestControllerParentsTest class.
 * @author jeffrey
 * @version v1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestControllerParentsTest {
  /**
   * WebTestClient.
   */
  @Autowired
  private WebTestClient webTestClient;
  /**
   * Reactive Repository.
   */
  @Autowired
  private ReactiveRepository repository;
  /**
   * unit test.
   */
  
  @Test
  public void searchbyName() {

    Parents parents = repository.findByFullName("jeffrey").blockFirst();
    if (parents != null) {
      webTestClient.get()
          .uri("/Parents/v1.0/names/{fullName}", Collections
          .singletonMap("fullName", parents.getFullName()))
          .accept(MediaType.APPLICATION_JSON_UTF8)
          .exchange()
          .expectStatus().isOk()
          .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
          .expectBodyList(Parents.class)
          .consumeWith(response -> {
            List<Parents> parentslist = response.getResponseBody();
            parentslist.forEach(show -> {
              System.out.println(show.getId());
              System.out.println(show.getFullName());
              System.out.println(show.getGender());
              System.out.println(show.getDateofBirth());
              System.out.println(show.getTypeDocument());
              System.out.println(show.getDocumentNumber());
            });
          });
    }
  }
  /**
   * unit test.
   */
  
  @Test
  public void searchbyDocument() {

    Parents parents = repository.findByDocumentNumber("47704995").block();
    if (parents != null) {
      webTestClient.get()
          .uri("/Parents/v1.0/documents/{document}", Collections
          .singletonMap("document", parents.getDocumentNumber()))
          .accept(MediaType.APPLICATION_JSON_UTF8)
          .exchange()
          .expectStatus().isOk()
          .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
          .expectBody(Parents.class)
          .consumeWith(response -> {
            Assertions.assertThat(response.getResponseBody()).isNotNull();
          });
    }
  } 
  /**
   * unit test.
   */
  
//  @Test
//  public void createParents() throws ParseException {
//
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    Date date = sdf.parse("2019-09-16");
//    Parents parent = new Parents("f1", "richard", "m", date, "dni", "4770888","1","nueva");
//    webTestClient.post()
//        .uri("/Parents/v1.0/")
//        .contentType(MediaType.APPLICATION_JSON_UTF8)
//        .accept(MediaType.APPLICATION_JSON_UTF8)
//        .body(Mono.just(parent), Parents.class)
//        .exchange()
//        .expectStatus().isCreated();
//  }
  /**
   * unit test.
   */
  
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
          parents.forEach(p -> {
            System.out.println(p.getId());
            System.out.println(p.getFullName());
            System.out.println(p.getGender());
            System.out.println(p.getDateofBirth());
            System.out.println(p.getTypeDocument());
            System.out.println(p.getDocumentNumber());
        
          });
        });
  }
  /**
   * unit test.
   */
  
  @Test
  public void updateStudent() throws ParseException {

    Parents parents = repository.findById("1").block();
    if (parents != null) {
      Parents newParents = new Parents(
          parents.getId(), "Jeff", "m", LocalDate.of(1993, 2, 25), "dni", "159748","1","nueva");
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
  }
  /**
   * unit test.
   */
  
  @Test
  public void deleteStudents() { 

    Parents parents = repository.findById("1").block();
    if (parents != null) {
      webTestClient.delete()
        .uri("/Parents/v1.0/{id}", Collections.singletonMap("id", parents.getId()))
        .exchange()
        .expectStatus().isNoContent();
    }       
  }
}
