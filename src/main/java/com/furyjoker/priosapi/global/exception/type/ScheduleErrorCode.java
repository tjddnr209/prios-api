package com.furyjoker.priosapi.global.exception.type;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ScheduleErrorCode implements ErrorCode {

    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "해당 일정에 접근할 수 없습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;

    ScheduleErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
