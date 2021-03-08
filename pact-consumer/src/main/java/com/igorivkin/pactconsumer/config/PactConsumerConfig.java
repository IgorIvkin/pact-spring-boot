package com.igorivkin.pactconsumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PactConsumerConfig {

    @Value("${application.provider.base-url}")
    private String providerBaseUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().rootUri(providerBaseUrl).build();
    }
}
