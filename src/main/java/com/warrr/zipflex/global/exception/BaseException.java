package com.warrr.zipflex.global.exception;

import com.warrr.zipflex.global.response.BaseResponseStatus;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final BaseResponseStatus status;

    public BaseException(BaseResponseStatus status) {
        this.status = status;
    }
}
