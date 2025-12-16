package com.chaewookim.accountbookformoms.domain.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest (

    @Email(message = "이메일의 형식에 맞지 않습니다.")
    String email,

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    String password
) {
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
