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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

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
        given(bookSearchService.searchBooks(any(), any())).willReturn(PageResponse.success(10, 10, 1, Arrays.asList()));

        // when
        final ResultActions actions = mockMvc.perform(get("/search/book")
                .param("query", "상실의시대")

        )
                .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("code").value(ErrorCode.CD_0000.getCode()))
                .andExpect(jsonPath("message").value(ErrorCode.CD_0000.getMessage()));
    }

    @Test
    public void 책조회_필수값_확인() throws Exception {

        // given
        given(bookSearchService.searchBooks(any(), any())).willReturn(PageResponse.success(10, 10, 1, Arrays.asList()));

        // when
        final ResultActions actions = mockMvc.perform(get("/search/book")
                .param("query", "")

        )
                .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().is4xxClientError())
                .andExpect(jsonPath("code").value(ErrorCode.CD_S001.getCode()))
        ;
    }

    @Test
    public void 책조회__요청파라미터_검증_PAGE() throws Exception {

        // given
        given(bookSearchService.searchBooks(any(), any())).willReturn(PageResponse.success(10, 10, 1, Arrays.asList()));

        // when
        final ResultActions actions = mockMvc.perform(get("/search/book")
                .param("query", "상실의시대")
                .param("page", "101")
                .param("display", "50")

        )
                .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().is4xxClientError())
                .andExpect(jsonPath("code").value(ErrorCode.CD_S001.getCode()))
        ;
    }

    @Test
    public void 책조회__요청파라미터_검증_DISPALY() throws Exception {

        // given
        given(bookSearchService.searchBooks(any(), any())).willReturn(PageResponse.success(10, 10, 1, Arrays.asList()));

        // when
        final ResultActions actions = mockMvc.perform(get("/search/book")
                .param("query", "상실의시대")
                .param("page", "1")
                .param("display", "101")

        )
                .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().is4xxClientError())
                .andExpect(jsonPath("code").value(ErrorCode.CD_S001.getCode()))
        ;
    }

    @Test
    public void 책상세조회() throws Exception {

        // given
        given(bookSearchService.searchBookDetail(any(), any())).willReturn(DataResponse.success(new BookInfo()));

        // when
        final ResultActions actions = mockMvc.perform(get("/search/book/detail")
                .param("isbn", "상실의시대")

        )
                .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ErrorCode.CD_0000.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.CD_0000.getMessage()))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @Test
    public void 책상세_필수값() throws Exception {

        // given
        given(bookSearchService.searchBookDetail(any(), any())).willReturn(DataResponse.success(new BookInfo()));

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
