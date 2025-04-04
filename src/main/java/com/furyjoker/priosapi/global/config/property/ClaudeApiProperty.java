package com.furyjoker.priosapi.global.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "claude.api-key")
public class ClaudeApiProperty {

    private String local;
    private String dev;

    public String getCurrentKey(String profile) {
        return switch (profile) {
            case "local" -> local;
            case "dev" -> dev;
            default -> throw new IllegalArgumentException("지원하지 않는 profile: " + profile);
        };
    }
}
