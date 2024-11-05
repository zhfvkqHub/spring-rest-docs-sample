package com.raonsecure.sample.notice.repository;

import com.raonsecure.sample.notice.dto.NoticeListRequestDto;
import com.raonsecure.sample.notice.dto.NoticeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeRepositoryCustom {
    Page<NoticeResponseDto> findNotices(NoticeListRequestDto requestDto, Pageable pageable);
}
