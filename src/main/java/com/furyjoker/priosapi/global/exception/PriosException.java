package com.furyjoker.priosapi.global.exception;

import com.furyjoker.priosapi.global.exception.type.ErrorCode;
import lombok.Getter;

@Getter
public class PriosException extends RuntimeException {

    private final ErrorCode errorCode;

    public PriosException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
