package com.official.prios_api.global.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "claude.ai")
public class ClaudeProperties {
    private String apiKey;
    private String url;
}
