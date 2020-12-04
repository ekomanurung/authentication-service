package com.gusuran.authentication.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "authentication")
@Configuration
@Data
public class AuthenticationProperties {
  private int maxLoginAttempt;
}
