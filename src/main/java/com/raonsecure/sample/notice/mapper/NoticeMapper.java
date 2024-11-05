package com.raonsecure.sample.notice.mapper;

import com.raonsecure.sample.entity.Notice;
import com.raonsecure.sample.notice.dto.NoticeResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NoticeMapper {
        NoticeResponseDto toResponseDto(Notice notice);
}
