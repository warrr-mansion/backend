package com.warrr.zipflex.global.exception;

import static com.warrr.zipflex.global.response.BaseResponseStatus.INTERNAL_SERVER_ERROR;
import static com.warrr.zipflex.global.response.BaseResponseStatus.INVALID_INPUT_VALUE;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.warrr.zipflex.global.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 전역 예외 처리 및 JSON 형식의 일관된 에러 응답 제공

    /**
     * BaseException 예외 처리.
     */
    @ExceptionHandler(BaseException.class)
    protected BaseResponse<Void> baseError(BaseException e) {
        log.error("BaseException -> {} ({})", e.getStatus(), e.getStatus().getMessage(), e);

        return new BaseResponse<>(e.getStatus());
    }

    /**
     * Validation 유효성 검사 실패 예외 처리.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected BaseResponse<Void> handleValidException(MethodArgumentNotValidException e) {
        String message = e.getFieldErrors().get(0).getDefaultMessage();
        log.error("MethodArgumentNotValidException -> {}", message);

        return new BaseResponse<>(INVALID_INPUT_VALUE, message);
    }

    /**
     * 예기치 않은 에러. RuntimeException 예외 처리
     */
    @ExceptionHandler(RuntimeException.class)
    protected BaseResponse<Void> runtimeError(RuntimeException e) {
        log.error("RuntimeException -> {}", e.getMessage(), e);

        return new BaseResponse<>(INTERNAL_SERVER_ERROR, e.getMessage());
    }

}
