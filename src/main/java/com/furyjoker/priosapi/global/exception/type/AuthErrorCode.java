package com.furyjoker.priosapi.global.exception.type;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AuthErrorCode implements ErrorCode {

    FAIL_TO_GET_TOKEN(HttpStatus.BAD_REQUEST, "구글 토큰 발급에 실패했습니다."),
    FAIL_TO_GET_USER_INFO(HttpStatus.BAD_REQUEST, "구글 사용자 정보 조회 실패"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다.");

    private final HttpStatus status;
    private final String message;

    AuthErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
