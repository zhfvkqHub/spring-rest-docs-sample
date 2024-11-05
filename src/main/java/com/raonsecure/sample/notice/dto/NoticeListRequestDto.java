package com.raonsecure.sample.notice.dto;

import jakarta.validation.constraints.NotNull;

public record NoticeListRequestDto(
        String searchType,
        String searchKeyword,
        @NotNull(message = "validation.page.required")
        Integer page
) {
}
