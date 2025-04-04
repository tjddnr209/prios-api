package com.furyjoker.priosapi.ai.client;

import com.furyjoker.priosapi.global.config.property.ClaudeApiProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClaudeClient {

    private final WebClient webClient;
    private final ClaudeApiProperty claudeApiProperty;

    @Value("${spring.profiles.active}")
    private String profile;

    public List<Long> getPrioritizedList(String prompt) {
        String apiKey = claudeApiProperty.getCurrentKey(profile);

        String response = webClient.post()
                .uri("https://api.anthropic.com/v1/messages")
                .header("x-api-key", apiKey)
                .header("anthropic-version", "2023-06-01")
                .bodyValue(buildRequestBody(prompt))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info("Claude 응답: {}", response);

        return parseResponse(response);
    }

    private String buildRequestBody(String prompt) {
        return """
        {
          "model": "claude-3-haiku-20240307",
          "max_tokens": 100,
          "temperature": 0.3,
          "messages": [
            {
              "role": "user",
              "content": "%s"
            }
          ]
        }
        """.formatted(prompt.replace("\"", "\\\""));
    }

    private List<Long> parseResponse(String response) {
        try {
            String jsonArray = response.replaceAll("[^\\[\\]0-9,]", "")
                    .replaceAll(".*?(\\[.*?\\]).*", "$1");

            return List.of(jsonArray.replace("[", "").replace("]", "").split(","))
                    .stream()
                    .map(String::trim)
                    .map(Long::parseLong)
                    .toList();

        } catch (Exception e) {
            log.error("Claude 응답 파싱 오류", e);
            throw new RuntimeException("AI 응답 파싱 실패");
        }
    }
}
