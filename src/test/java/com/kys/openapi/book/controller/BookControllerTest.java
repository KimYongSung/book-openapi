package com.kys.openapi.book.controller;

import com.kys.openapi.app.constants.ErrorCode;
import com.kys.openapi.app.result.DataResponse;
import com.kys.openapi.app.result.PageResponse;
import com.kys.openapi.book.dto.BookInfo;
import com.kys.openapi.book.service.BookSearchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerTest{

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private BookSearchService bookSearchService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void 책조회() throws Exception {

        // given
        List<BookInfo> data = Arrays.asList(makeBookInfo()
                                          , makeBookInfo()
                                          , makeBookInfo()
                                          , makeBookInfo()
                                          , makeBookInfo()
                                          , makeBookInfo()
                                          , makeBookInfo()
                                          , makeBookInfo()
                                          , makeBookInfo()
                                          , makeBookInfo());

        PageResponse<BookInfo> success = PageResponse.success(10, 10, 1, data);

        given(bookSearchService.searchBooks(any(), any())).willReturn(success);

        // when
        final ResultActions actions = mockMvc.perform(get("/search/book")
                                             .param("search", "상실의시대"))
                                             .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().isOk())
               .andExpect(jsonPath("$.code").value(ErrorCode.CD_0000.getCode()))
               .andExpect(jsonPath("$.message").value(ErrorCode.CD_0000.getMessage()))
               .andExpect(jsonPath("$.totalSize").value(10))
               .andExpect(jsonPath("$.size").value(10))
               .andExpect(jsonPath("$.page").value(1))
               .andExpect(jsonPath("$.data").isArray())
               .andExpect(jsonPath("$.data[0].title").isNotEmpty())
               .andExpect(jsonPath("$.data[0].thumbnail").isNotEmpty())
               .andExpect(jsonPath("$.data[0].contents").isNotEmpty())
               .andExpect(jsonPath("$.data[0].isbn").isNotEmpty())
               .andExpect(jsonPath("$.data[0].author").isNotEmpty())
               .andExpect(jsonPath("$.data[0].publisher").isNotEmpty())
               .andExpect(jsonPath("$.data[0].datetime").isNotEmpty())
               .andExpect(jsonPath("$.data[0].price").isNotEmpty())
               .andExpect(jsonPath("$.data[0].salePrice").isNotEmpty())
        ;
    }

    @Test
    public void 책조회_필수값_확인() throws Exception {

        // given
        given(bookSearchService.searchBooks(any(), any())).willReturn(PageResponse.success(10, 10, 1, Arrays.asList()));

        // when
        final ResultActions actions = mockMvc.perform(get("/search/book")
                                             .param("search", ""))
                                             .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().is4xxClientError())
               .andExpect(jsonPath("code").value(ErrorCode.CD_S001.getCode()))
               .andExpect(jsonPath("message").value("검색 키워드가 누락되었습니다."))
        ;
    }

    @Test
    public void 책조회__요청파라미터_검증_start() throws Exception {

        // given
        given(bookSearchService.searchBooks(any(), any())).willReturn(PageResponse.success(10, 10, 1, Arrays.asList()));

        // when
        final ResultActions actions = mockMvc.perform(get("/search/book")
                                             .param("search", "상실의시대")
                                             .param("start", "101")
                                             .param("length", "50"))
                                             .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().is4xxClientError())
               .andExpect(jsonPath("code").value(ErrorCode.CD_S001.getCode()))
               .andExpect(jsonPath("message").value("start는 0 ~ 100 범위로 요청 가능합니다."))
        ;
    }

    @Test
    public void 책조회__요청파라미터_검증_length() throws Exception {

        // given
        given(bookSearchService.searchBooks(any(), any())).willReturn(PageResponse.success(10, 10, 1, Arrays.asList()));

        // when
        final ResultActions actions = mockMvc.perform(get("/search/book")
                                             .param("search", "상실의시대")
                                             .param("start", "1")
                                             .param("length", "101"))
                                             .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().is4xxClientError())
               .andExpect(jsonPath("code").value(ErrorCode.CD_S001.getCode()))
               .andExpect(jsonPath("message").value("length는 0 ~ 50 범위로 요청 가능합니다."))
        ;
    }

    @Test
    public void 책상세조회() throws Exception {

        // given
        given(bookSearchService.searchBookDetail(any(), any())).willReturn(DataResponse.success(makeBookInfo()));

        // when
        final ResultActions actions = mockMvc.perform(get("/search/book/detail")
                                             .param("isbn", "상실의시대"))
                                             .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().isOk())
               .andExpect(jsonPath("$.code").value(ErrorCode.CD_0000.getCode()))
               .andExpect(jsonPath("$.message").value(ErrorCode.CD_0000.getMessage()))
               .andExpect(jsonPath("$.data").isNotEmpty())
               .andExpect(jsonPath("$.data.title").isNotEmpty())
               .andExpect(jsonPath("$.data.thumbnail").isNotEmpty())
               .andExpect(jsonPath("$.data.contents").isNotEmpty())
               .andExpect(jsonPath("$.data.isbn").isNotEmpty())
               .andExpect(jsonPath("$.data.author").isNotEmpty())
               .andExpect(jsonPath("$.data.publisher").isNotEmpty())
               .andExpect(jsonPath("$.data.datetime").isNotEmpty())
               .andExpect(jsonPath("$.data.price").isNotEmpty())
               .andExpect(jsonPath("$.data.salePrice").isNotEmpty())
        ;
    }

    private BookInfo makeBookInfo() {
        return BookInfo.builder()
                       .title("상실의 시대")
                       .thumbnail("썸네일")
                       .contents("컨텐츠")
                       .isbn("ISBN")
                       .author("저자 정보")
                       .publisher("출판사")
                       .datetime("발행일")
                       .price(1000)
                       .salePrice(900)
                       .build()
                ;
    }

    @Test
    public void 책상세_필수값() throws Exception {

        // given
        given(bookSearchService.searchBookDetail(any(), any())).willReturn(DataResponse.success(makeBookInfo()));

        // when
        final ResultActions actions = mockMvc.perform(get("/search/book/detail")
                .param("isbn", "")

        )
                .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.code").value(ErrorCode.CD_S001.getCode()))
                .andExpect(jsonPath("$.message").isNotEmpty())
        ;
    }


}
