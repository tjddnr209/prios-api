package com.furyjoker.priosapi.global.exception.type;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TagErrorCode implements ErrorCode {

    TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "태그를 찾을 수 없습니다."),
    TAG_FORBIDDEN(HttpStatus.FORBIDDEN, "해당 태그에 접근할 수 없습니다."),
    CANNOT_DELETE_DEFAULT_TAG(HttpStatus.BAD_REQUEST, "기본 태그는 삭제할 수 없습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;

    TagErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
