package com.official.prios_api.global.error;

import com.official.prios_api.global.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ApiResponse<String> handleApiException(ApiException e) {
        return ApiResponse.error(e.getErrorCode().getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<String> handleException(Exception e) {
        return ApiResponse.error("예상치 못한 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}