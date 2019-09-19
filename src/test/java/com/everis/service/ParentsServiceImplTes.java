package com.everis.service;

import static org.mockito.Mockito.when;

import com.everis.model.Parents;
import com.everis.repository.ReactiveRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.reactivestreams.Publisher;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ParentsServiceImplTes {

  @Mock
  ReactiveRepository repository;

  @InjectMocks
  ParentsServiceImpl parentService;

  @Test
  public void searchbyDocument() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date fecha = sdf.parse("2019-09-16");
    Parents parent = new Parents("1", "richard", "m", fecha, "dni", "47701888", "1", "nueva");
    String document = "47701888";
    when(repository.findByDocumentNumber(document)).thenReturn(Mono.just(parent));
    Mono<Parents> actual = parentService.searchbyDocument(document);
    assertResults(actual,parent);
  }

  @Test
  public void createParents() throws ParseException {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date fecha = sdf.parse("2019-09-16");
    Parents parent = new Parents("1", "richard", "m", fecha, "dni", "47701888", "1", "nueva");
    when(repository.save(parent)).thenReturn(Mono.just(parent));
    Mono<Parents> actual = parentService.createParents(parent);
    assertResults(actual, parent);
  }
 
  @Test
  public void allParents() throws ParseException {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date fecha = sdf.parse("2019-09-16");
    Parents parent = new Parents("1", "richard", "m", fecha, "dni", "4770888", "1", "nueva");
    when(parentService.allParents()).thenReturn(Flux.just(parent));
    Flux<Parents> actual = parentService.allParents();
    assertResults(actual, parent);
  }

  @Test
  public void delete() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date fecha = sdf.parse("2019-09-16");
    Parents parent = new Parents("1", "richard", "m", fecha, "dni", "4770888", "1", "nueva");
    when(repository.delete(parent)).thenReturn(Mono.empty());
  }

  private void assertResults(Publisher<Parents> publisher, Parents... expectedProducts) {
    StepVerifier
          .create(publisher)
          .expectNext(expectedProducts)
          .verifyComplete();
  }
}
