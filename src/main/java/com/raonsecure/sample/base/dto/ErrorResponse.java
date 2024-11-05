package com.raonsecure.sample.base.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ErrorResponse(
        HttpStatus status,
        String code,
        String message
) {
}
