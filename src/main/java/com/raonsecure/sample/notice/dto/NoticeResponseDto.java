package com.raonsecure.sample.notice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;

import java.time.Instant;

public record NoticeResponseDto(
        Long id,
        String title,
        String content,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        Instant createdDt,
        String createdBy
) {
        @QueryProjection
        public NoticeResponseDto {
        }
}
