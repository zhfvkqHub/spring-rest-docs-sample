package com.raonsecure.sample.login.dto;

public record LoginResponseDto(
        Long id,
        String loginId,
        String role,
        String accessToken
) {
}
