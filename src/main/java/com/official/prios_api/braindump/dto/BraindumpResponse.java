package com.official.prios_api.braindump.dto;

import com.official.prios_api.braindump.domain.Braindump;

import java.time.LocalDateTime;

public record BraindumpResponse(
        Long id,
        String content,
        LocalDateTime createdAt
) {
    public static BraindumpResponse from(Braindump entity) {
        return new BraindumpResponse(entity.getId(), entity.getContent(), entity.getCreatedAt());
    }
}

