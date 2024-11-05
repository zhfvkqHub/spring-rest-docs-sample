package com.raonsecure.sample.login.dto;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequestDto(
        @NotEmpty(message = "validation.loginId.required")
        String loginId,
        @NotEmpty(message = "validation.password.required")
        String password
) {
        @Override
        public String toString() {
                return "LoginRequestDto{" +
                        "loginId='" + loginId + '\'' +
                        '}';
        }
}
