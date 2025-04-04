package com.furyjoker.priosapi.global.exception;

import com.furyjoker.priosapi.global.exception.type.ErrorCode;
import com.furyjoker.priosapi.global.response.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PriosException.class)
    public ApiResponse<?> handlePriosException(PriosException e) {
        ErrorCode code = e.getErrorCode();
        return ApiResponse.error(code.getStatus().value(), code.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleOtherException(Exception e) {
        return ApiResponse.error(500, "서버 내부 오류가 발생했습니다.");
    }
}
