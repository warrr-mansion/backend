package com.warrr.zipflex.api.auth.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignUpRequestVo {

    @NotBlank(message = "닉네임은 필수 입력 항목입니다.")
    @Pattern(regexp = "^[^'\"]{1,12}$", message = "닉네임은 따옴표(' 또는 \") 없이 1자 이상 12자 이하로 입력해야 합니다.")
    @Schema(description = "닉네임 (1~12자, 따옴표 불가)", defaultValue = "nickname")
    private String nickname;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Schema(defaultValue = "test@zipflex.com")
    private String email;

    @Pattern(regexp = "^(?=\\S+$)(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,20}$",
                    message = "비밀번호는 공백 없이 8~20자, 영문, 숫자, 특수문자를 포함해야 합니다.")
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Schema(description = "비밀번호 (8~20자, 공백 없이 영문+숫자+특수문자 포함)", defaultValue = "Aa123456!")
    private String password;

}
