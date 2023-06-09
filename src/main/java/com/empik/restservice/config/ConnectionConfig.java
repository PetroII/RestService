package com.empik.restservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties(prefix = "application.connection")
@Data
public class ConnectionConfig {
    private String api;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
