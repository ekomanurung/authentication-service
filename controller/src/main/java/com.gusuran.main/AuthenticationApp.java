package com.gusuran.main;

import com.gusuran.properties.ErrorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        ErrorsProperties.class
})
public class AuthenticationApp {
    public static void main(String [] args){
        SpringApplication.run(AuthenticationApp.class, args);
    }
}