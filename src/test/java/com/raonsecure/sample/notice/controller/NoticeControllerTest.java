package com.raonsecure.sample.notice.controller;

import com.raonsecure.sample.RestDocsConfiguration;
import com.raonsecure.sample.base.dto.CustomPage;
import com.raonsecure.sample.notice.dto.NoticeListRequestDto;
import com.raonsecure.sample.notice.dto.NoticeResponseDto;
import com.raonsecure.sample.notice.service.NoticeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoticeController.class)
@ExtendWith(RestDocumentationExtension.class)
@Import(RestDocsConfiguration.class)
class NoticeControllerTest {

    @MockBean
    private NoticeService noticeService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestDocumentationResultHandler restDocs;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new NoticeController(noticeService))
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
                .alwaysDo(restDocs)
                .build();
    }

    @Test
    @DisplayName("공지사항 목록 조회 API 문서 생성")
    void getAllNotices() throws Exception {
        // 샘플 데이터 설정
        NoticeResponseDto sampleNotice = new NoticeResponseDto(1L, "Title", "Content", Instant.now(), "SYSTEM_ADMIN");
        CustomPage<NoticeResponseDto> samplePage = new CustomPage<>(
                1, 10, 1, Collections.singletonList(sampleNotice)
        );
        // Mock 설정
        when(noticeService.findAllNotices(any(NoticeListRequestDto.class))).thenReturn(samplePage);

        mockMvc.perform(get("/api/notice")
                        .param("searchType", "TITLE")
                        .param("searchKeyword", "Title")
                        .param("page", "0")
                )
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                        queryParameters(
                                parameterWithName("searchType").description("검색 타입"),
                                parameterWithName("searchKeyword").description("검색어"),
                                parameterWithName("page").description("페이지 번호")
                        ),
                        responseFields(
                                fieldWithPath("pageNumber").type(JsonFieldType.NUMBER).description("현재 페이지 번호"),
                                fieldWithPath("pageSize").type(JsonFieldType.NUMBER).description("페이지당 요소 수"),
                                fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("총 요소 수"),
                                fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("총 페이지 수"),
                                fieldWithPath("first").type(JsonFieldType.BOOLEAN).description("첫 페이지 여부"),
                                fieldWithPath("last").type(JsonFieldType.BOOLEAN).description("마지막 페이지 여부"),
                                fieldWithPath("content[].id").type(JsonFieldType.NUMBER).description("공지사항 ID"),
                                fieldWithPath("content[].title").type(JsonFieldType.STRING).description("공지사항 제목"),
                                fieldWithPath("content[].content").type(JsonFieldType.STRING).description("공지사항 내용"),
                                fieldWithPath("content[].createdDt").type(JsonFieldType.STRING).description("공지사항 생성일"),
                                fieldWithPath("content[].createdBy").type(JsonFieldType.STRING).description("공지사항 작성자")
                        )
                ));
    }

    @Test
    @DisplayName("공지사항 상세 조회 API 문서 생성")
    void getNoticeById() throws Exception {
        // 샘플 데이터 설정
        NoticeResponseDto sampleNotice = new NoticeResponseDto(1L, "Title", "Content", Instant.now(), "SYSTEM_ADMIN");
        // Mock 설정
        when(noticeService.findNoticeById(1L)).thenReturn(sampleNotice);

        mockMvc.perform(get("/api/notice/1"))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("공지사항 ID"),
                                fieldWithPath("title").type(JsonFieldType.STRING).description("공지사항 제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("공지사항 내용"),
                                fieldWithPath("createdDt").type(JsonFieldType.STRING).description("공지사항 생성일"),
                                fieldWithPath("createdBy").type(JsonFieldType.STRING).description("공지사항 작성자")
                        )
                ));
    }

    @Test
    @DisplayName("공지사항 생성 API 문서 생성")
    void createNotice() throws Exception {
        // 서비스 메서드 목킹
        NoticeResponseDto sampleNotice = new NoticeResponseDto(
                1L, "Title", "Content", Instant.now(), "SYSTEM_ADMIN");
        when(noticeService.createNotice(any())).thenReturn(sampleNotice);

        // API 요청 보내기
        mockMvc.perform(post("/api/notice")
                        .content(
                                """
                                {
                                    "title": "Title",
                                    "content": "Content"
                                }
                                """
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                )
                // 응답 상태 검증
                .andExpect(status().isCreated())
                // 문서화
                .andDo(restDocs.document(
                        requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("공지사항 제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("공지사항 내용")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("공지사항 ID"),
                                fieldWithPath("title").type(JsonFieldType.STRING).description("공지사항 제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("공지사항 내용"),
                                fieldWithPath("createdDt").type(JsonFieldType.STRING).description("공지사항 생성일"),
                                fieldWithPath("createdBy").type(JsonFieldType.STRING).description("공지사항 작성자")
                        )
                ));
    }

    @Test
    @DisplayName("공지사항 수정 API 문서 생성")
    void updateNotice() throws Exception {
        // 샘플 데이터 설정
        NoticeResponseDto sampleNotice = new NoticeResponseDto(1L, "Title2", "Content2", Instant.now(), "SYSTEM_ADMIN");
        // Mock 설정
        when(noticeService.updateNotice(eq(1L), any())).thenReturn(sampleNotice);

        mockMvc.perform(put("/api/notice/1")
                        .content(
                                """
                                {
                                    "title": "Title2",
                                    "content": "Content2"
                                }
                                """
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                        requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("공지사항 제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("공지사항 내용")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("공지사항 ID"),
                                fieldWithPath("title").type(JsonFieldType.STRING).description("공지사항 제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("공지사항 내용"),
                                fieldWithPath("createdDt").type(JsonFieldType.STRING).description("공지사항 생성일"),
                                fieldWithPath("createdBy").type(JsonFieldType.STRING).description("공지사항 작성자")
                        )
                ));
    }

    @Test
    @DisplayName("공지사항 삭제 API 문서 생성")
    void deleteNotice() throws Exception {
        mockMvc.perform(delete("/api/notice/1")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

}
