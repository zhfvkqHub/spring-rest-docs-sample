package com.raonsecure.sample.notice.dto;

import jakarta.validation.constraints.Size;

import static com.raonsecure.sample.base.dto.ValidTypeSize.TITLE;

public record UpdateNoticeDto(
        @Size(max = TITLE, message = "validation.title.size")
        String title,
        String content
) {
}
