package com.furyjoker.priosapi.global.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum OrderInfoErrorCode implements ErrorCode {
    ORDER_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "OrderInfo 우선순위 업데이트에 실패했습니다.");

    private final HttpStatus status;
    private final String message;
}
