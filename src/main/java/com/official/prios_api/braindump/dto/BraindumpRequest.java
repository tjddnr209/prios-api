package com.official.prios_api.braindump.dto;

import jakarta.validation.constraints.NotBlank;

public record BraindumpRequest(
        @NotBlank(message = "내용을 입력해주세요.")
        String content
) {}

