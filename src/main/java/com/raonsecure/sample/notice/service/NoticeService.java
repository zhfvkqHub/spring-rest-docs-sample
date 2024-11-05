package com.raonsecure.sample.notice.service;

import com.raonsecure.sample.base.dto.CustomPage;
import com.raonsecure.sample.base.errorcode.ResponseCode;
import com.raonsecure.sample.base.exception.BusinessException;
import com.raonsecure.sample.entity.Notice;
import com.raonsecure.sample.notice.dto.CreateNoticeDto;
import com.raonsecure.sample.notice.dto.NoticeListRequestDto;
import com.raonsecure.sample.notice.dto.NoticeResponseDto;
import com.raonsecure.sample.notice.dto.UpdateNoticeDto;
import com.raonsecure.sample.notice.mapper.NoticeMapper;
import com.raonsecure.sample.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeMapper noticeMapper;

    /**
     * 공지사항 목록 조회
     */
    @Transactional(readOnly = true)
    public CustomPage<NoticeResponseDto> findAllNotices(NoticeListRequestDto requestDto) {
        PageRequest pageRequest = PageRequest.of(requestDto.page(), 10);
        Page<NoticeResponseDto> pageResult = noticeRepository.findNotices(requestDto, pageRequest);

        return new CustomPage<>(pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements(), pageResult.getContent());
    }

    /**
     * 공지사항 상세 조회
     */
    @Transactional(readOnly = true)
    public NoticeResponseDto findNoticeById(Long id) {
        Notice notice = findByIdOrFail(id);
        return noticeMapper.toResponseDto(notice);
    }

    /**
     * 공지사항 생성
     */
    @Transactional
    public NoticeResponseDto createNotice(CreateNoticeDto createNoticeDto) {
        Notice notice = Notice.create(
                createNoticeDto.title(),
                createNoticeDto.content()
        );
        notice = noticeRepository.save(notice);
        return noticeMapper.toResponseDto(notice);
    }

    /**
     * 공지사항 정보 업데이트
     */
    @Transactional
    public NoticeResponseDto updateNotice(Long id, UpdateNoticeDto createNoticeDto) {
        Notice notice = findByIdOrFail(id);
        notice.update(
                createNoticeDto.title(),
                createNoticeDto.content()
        );
        return noticeMapper.toResponseDto(notice);
    }

    /**
     * 공지사항 삭제
     */
    @Transactional
    public void deleteNotice(Long id) {
        Notice notice = findByIdOrFail(id);
        notice.delete();
    }

    private Notice findByIdOrFail(Long id) {
        return noticeRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ResponseCode.NOT_FOUND_NOTICE));
    }
}
