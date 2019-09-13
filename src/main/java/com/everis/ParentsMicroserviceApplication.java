package com.everis;

import com.everis.model.Parents;
import com.everis.repository.ReactiveRepository;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Flux;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@SpringBootApplication
@EnableSwagger2WebFlux
public class ParentsMicroserviceApplication {

//  private Logger log = LoggerFactory.getLogger(this.getClass());
//
//  @Bean
//CommandLineRunner start(ReactiveRepository repository) {
//    return args -> {
//
//      Flux.just(
//new Parents("1","jeffrey","m","2019-02-17","dni","1234567"),
//new Parents("2","Jeffrey","m","2019-02-17","dni","1234567"))
//.flatMap(repository::save)
//        .subscribe(parents -> log.info("Parents: {}", parents));
//    };
//  }

  public static void main(String[] args) {
    SpringApplication.run(ParentsMicroserviceApplication.class, args);
  }
}
