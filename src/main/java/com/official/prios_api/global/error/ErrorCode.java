package com.official.prios_api.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    NOT_FOUND("요청한 리소스를 찾을 수 없습니다."),
    BAD_REQUEST("잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR("서버 오류가 발생했습니다.");

    private final String message;
}

