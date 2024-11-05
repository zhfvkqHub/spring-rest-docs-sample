package com.raonsecure.sample.notice.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.raonsecure.sample.base.constant.SearchBy;
import com.raonsecure.sample.notice.dto.NoticeListRequestDto;
import com.raonsecure.sample.notice.dto.NoticeResponseDto;
import com.raonsecure.sample.notice.dto.QNoticeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.raonsecure.sample.entity.QNotice.notice;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<NoticeResponseDto> findNotices(NoticeListRequestDto requestDto, Pageable pageable) {
        List<NoticeResponseDto> noticeResults = queryFactory.select(
                        new QNoticeResponseDto(
                                notice.id,
                                notice.title,
                                notice.content,
                                notice.createdDt,
                                notice.createdBy
                        ))
                .from(notice)
                .where(
                        searchFilter(requestDto.searchType(), requestDto.searchKeyword()),
                        notice.deleted.eq(false)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(notice.createdDt.desc())
                .fetch();

        return PageableExecutionUtils.getPage(noticeResults, pageable, () -> getCount(requestDto));
    }

    private long getCount(NoticeListRequestDto requestDto) {
        return queryFactory.select(notice.count())
                .from(notice)
                .where(
                        searchFilter(requestDto.searchType(), requestDto.searchKeyword()),
                        notice.deleted.eq(false)
                )
                .fetchOne();
    }

    private BooleanExpression searchFilter(String searchType, String searchKeyword) {
        if (!hasText(searchKeyword)) { return null; }
        if (!hasText(searchType)) { searchType = SearchBy.ALL.name(); }

        return switch (SearchBy.fromValue(searchType)) {
            case ALL, TITLE -> notice.title.containsIgnoreCase(searchKeyword);
            default -> throw new IllegalArgumentException("Invalid search type: " + searchType);
        };
    }
}