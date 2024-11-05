package com.raonsecure.sample.notice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import static com.raonsecure.sample.base.dto.ValidTypeSize.TITLE;

public record CreateNoticeDto(
        @Size(max = TITLE, message = "validation.title.size")
        String title,
        @NotEmpty(message = "validation.content.required")
        String content
) {
}
