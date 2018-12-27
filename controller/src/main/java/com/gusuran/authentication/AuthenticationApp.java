package com.gusuran.authentication;

import com.gusuran.authentication.properties.ErrorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties({
        ErrorsProperties.class
})
@ComponentScan("com.gusuran")
public class AuthenticationApp {
    public static void main(String [] args){
        SpringApplication.run(AuthenticationApp.class, args);
    }
}
