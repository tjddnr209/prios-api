package com.official.prios_api.global.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "공통 응답 포맷")
public record ApiResponse<T>(
        @Schema(description = "응답 메시지", example = "success")
        String message,

        @Schema(description = "실제 데이터")
        T data,

        @Schema(description = "상태 코드")
        int statusCode
) {
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>("success", data, 200);
    }

    public static ApiResponse<Void> ok() {
        return new ApiResponse<>("success", null, 200);
    }

    public static ApiResponse<String> error(String message) {
        return new ApiResponse<>(message, null, 400);
    }

    public static ApiResponse<String> error(String message, int statusCode) {
        return new ApiResponse<>(message, null, statusCode);
    }
}

