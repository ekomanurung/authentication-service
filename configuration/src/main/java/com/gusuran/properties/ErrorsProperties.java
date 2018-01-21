package com.gusuran.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Data
@ConfigurationProperties("errors")
public class ErrorsProperties {

    private Map<String, String> mapping = new HashMap<>();
}
