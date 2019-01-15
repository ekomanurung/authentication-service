package com.gusuran.authentication.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfiguration {

  @Bean
  public static DeserializationProblemHandler deserializationProblemHandler() {
    return new DeserializationProblemHandler();
  }

  @Bean
  public ObjectMapper createObjectMapper() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
    objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
    objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
    objectMapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
    objectMapper.addHandler(deserializationProblemHandler());
    return objectMapper;
  }
}
