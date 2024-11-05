package com.raonsecure.sample.notice.controller;

import com.raonsecure.sample.base.dto.CustomPage;
import com.raonsecure.sample.notice.dto.CreateNoticeDto;
import com.raonsecure.sample.notice.dto.NoticeListRequestDto;
import com.raonsecure.sample.notice.dto.NoticeResponseDto;
import com.raonsecure.sample.notice.dto.UpdateNoticeDto;
import com.raonsecure.sample.notice.service.NoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * 공지사항 목록 조회
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomPage<NoticeResponseDto> getAllNotices(
            @Valid @ModelAttribute NoticeListRequestDto requestDto
    ) {
        return noticeService.findAllNotices(requestDto);
    }

    /**
     * 공지사항 상세 조회
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NoticeResponseDto getNoticeById(@PathVariable Long id) {
        return noticeService.findNoticeById(id);
    }

    /**
     * 공지사항 생성
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<NoticeResponseDto> createNotice(@Valid @RequestBody CreateNoticeDto noticeDto) {
        NoticeResponseDto notice = noticeService.createNotice(noticeDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(notice.id()).toUri();
        return ResponseEntity.created(location).body(notice);
    }

    /**
     * 공지사항 수정
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NoticeResponseDto updateNotice(@PathVariable Long id,
                                          @Valid @RequestBody UpdateNoticeDto noticeDto) {
        return noticeService.updateNotice(id, noticeDto);
    }

    /**
     * 공지사항 삭제
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
    }
}
