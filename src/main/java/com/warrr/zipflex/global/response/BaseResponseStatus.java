package com.warrr.zipflex.global.response;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    /**
     * 200: 요청 성공.
     **/
    SUCCESS(HttpStatus.OK, true, 200, "요청에 성공하였습니다."),

    /**
     * 400: 사용자 요청 에러.
     */
    ILLEGAL_ARGUMENT(HttpStatus.BAD_REQUEST, false, 400, "잘못된 요청입니다."), 
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, false, 401, "적절하지 않은 요청값입니다."),
    
    WRONG_JWT_TOKEN(HttpStatus.UNAUTHORIZED, false, 401, "인증 정보가 유효하지 않습니다. 다시 로그인해주시기 바랍니다."),
    FAILED_TO_LOGIN(HttpStatus.UNAUTHORIZED, false, 401, "아이디 또는 비밀번호가 올바르지 않습니다."),
    NO_SIGN_IN(HttpStatus.UNAUTHORIZED, false, 401, "로그인이 필요한 요청입니다. 다시 로그인해주세요."),
    DISABLED_USER(HttpStatus.FORBIDDEN, false, 403, "비활성화된 계정입니다. 관리자에게 문의해주시기 바랍니다."),
    NO_ACCESS_AUTHORITY(HttpStatus.FORBIDDEN, false, 403, "접근 권한이 없습니다. 관리자에게 문의해주시기 바랍니다."),
    NO_EXIST_USER(HttpStatus.NOT_FOUND, false, 404, "존재하지 않는 사용자입니다."),

    
    /**
     * 500: 기타 에러.
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 500, "서버에서 예기치 않은 오류가 발생했습니다."),

    /**
     * 600: 타입 에러.
     */
    INVALID_ROLE(HttpStatus.BAD_REQUEST, false, 601, "지원하지 않는 RoleType입니다."),
  
    /**
     * 700: House Domain 에러.
     */
    INVALID_BUILDING_TYPE(HttpStatus.BAD_REQUEST, false, 701, "지원하지 않는 BuildingType입니다.");

    private final HttpStatus httpStatus;
    private final boolean isSuccess;
    private final int code;
    private final String message;

}
