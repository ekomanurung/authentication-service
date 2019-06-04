package com.gusuran.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class AuthenticationApp {
  public static void main(String[] args) {
    SpringApplication.run(AuthenticationApp.class, args);
  }
}
