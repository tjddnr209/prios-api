package com.official.prios_api.global.config;

import io.netty.channel.ChannelOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import jakarta.annotation.PostConstruct;
import java.time.Duration;

@Slf4j
@Configuration
public class WebClientConfig {

    @Value("${external.claude.api-key}")
    private String apiKey;

    @Value("${external.claude.url}")
    private String baseUrl;

    @Value("${webclient.connect-timeout:3000}")
    private int connectTimeout;

    @Value("${webclient.read-timeout:5000}")
    private int readTimeout;

    @PostConstruct
    public void logProperties() {
        log.info("✅ Claude API Key Loaded: {}", apiKey != null);
        log.info("✅ Claude API Base URL: {}", baseUrl);
    }

    @Bean
    public WebClient claudeWebClient() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout)
                .responseTimeout(Duration.ofMillis(readTimeout));

        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("x-api-key", apiKey)
                .defaultHeader("anthropic-version", "2023-06-01")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .filter((request, next) -> {
                    log.debug("🌐 Sending request to: {}", request.url());
                    return next.exchange(request);
                })
                .build();
    }
}
