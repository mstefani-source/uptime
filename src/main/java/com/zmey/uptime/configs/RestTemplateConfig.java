package com.zmey.uptime.configs;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.lang.Arrays;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RestTemplateConfig {
    
    @Value("${restTemplate.maxConn:500}")
    private int maxConn;

    @Value("${restTemplate.connectTimeout:120000}")
    private int connTimeout;

    @Value("${http.client.connection-request-timeout:5000}")
    private int connectionRequestTimeout;

    @Value("${restTemplate.readTimeout:120000}")
    private int readTimeout;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        
        // Добавляем перехватчики для логирования
        restTemplate.setInterceptors(List.of(
            new LoggingInterceptor(),
            new UserAgentInterceptor("MyApp/1.0")
        ));
        
        // Настраиваем сообщения об ошибках
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = 
            new HttpComponentsClientHttpRequestFactory();
        
        factory.setHttpClient(httpClient());
        factory.setConnectTimeout(connTimeout);
        factory.setReadTimeout(readTimeout);
        factory.setConnectionRequestTimeout(connectionRequestTimeout);
        
        return factory;
    }

   @Bean
    public HttpClient httpClient() {
        PoolingHttpClientConnectionManager connectionManager = 
            new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(50);
        connectionManager.setDefaultMaxPerRoute(10);
        
        return (HttpClient) HttpClients.custom()
            .setConnectionManager(connectionManager)
            .build();
    }

    private RequestConfig requestConfig() {
        return RequestConfig.custom()
            .setConnectionRequestTimeout(connectionRequestTimeout)
            .setConnectTimeout(connTimeout)
            .setSocketTimeout(readTimeout)
            .setCookieSpec(CookieSpecs.STANDARD)
            .build();
    }

    private PoolingHttpClientConnectionManager connectionManager() {
        PoolingHttpClientConnectionManager cm = 
            new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(maxConn);
        cm.setDefaultMaxPerRoute(maxConn / 2);
        cm.setValidateAfterInactivity(5000);
        return cm;
    }

    // Перехватчик для логирования
    private static class LoggingInterceptor implements ClientHttpRequestInterceptor {
        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                ClientHttpRequestExecution execution) throws IOException, java.io.IOException {
            
            log.debug("Request: {} {}", request.getMethod(), request.getURI());
            
            ClientHttpResponse response = execution.execute(request, body);
            
            log.debug("Response: {} {}", response.getStatusCode(), response.getStatusText());
            
            return response;
        }
    }

    private static class UserAgentInterceptor implements ClientHttpRequestInterceptor {
        private final String userAgent;
        
        UserAgentInterceptor(String userAgent) {
            this.userAgent = userAgent;
        }
        
        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                ClientHttpRequestExecution execution) throws IOException, java.io.IOException {
            request.getHeaders().add("User-Agent", userAgent);
            return execution.execute(request, body);
        }
    }
}