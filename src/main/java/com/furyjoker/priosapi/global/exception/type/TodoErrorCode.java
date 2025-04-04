package com.furyjoker.priosapi.global.exception.type;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TodoErrorCode implements ErrorCode {

    TODO_NOT_FOUND(HttpStatus.NOT_FOUND, "할 일을 찾을 수 없습니다."),
    TODO_FORBIDDEN(HttpStatus.FORBIDDEN, "해당 할 일에 접근할 수 없습니다."),
    TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "태그를 찾을 수 없습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;

    TodoErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
