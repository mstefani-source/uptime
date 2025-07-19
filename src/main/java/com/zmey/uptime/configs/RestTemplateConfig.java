package com.zmey.uptime.configs;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Value("${restTemplate.maxConn:500}")
    private int maxConn;

    @Value("${restTemplate.connectTimeout:120000}")
    private int connTimeout;

    @Bean
    public RestTemplate restTemplate() {
        final CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        return new RestTemplate();
    }
}
