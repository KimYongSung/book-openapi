package com.kys.openapi.keyword.controller;

import com.kys.openapi.app.constants.ErrorCode;
import com.kys.openapi.app.result.DataResponse;
import com.kys.openapi.app.result.PageResponse;
import com.kys.openapi.keyword.domain.KeyWordHistory;
import com.kys.openapi.keyword.dto.KeyWordCallInfo;
import com.kys.openapi.keyword.service.KeyWordService;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KeyWordControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private KeyWordService keyWordService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void top10조회() throws Exception {

        // given
        given(keyWordService.getKeyWordByTop10()).willReturn(top10Success());

        // when
        final ResultActions actions = mockMvc.perform(get("/keyWord/top10"))
                .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ErrorCode.CD_0000.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.CD_0000.getMessage()))
                .andExpect(jsonPath("$.data").isArray())
        ;
    }

    @Test
    public void 검색이력조회() throws Exception {

        // given
        given(keyWordService.getKeyWordHistory(any(), any())).willReturn(pageHistorySuccess());

        // when
        final ResultActions actions = mockMvc.perform(get("/keyWord/history")
                .param("page", "1")
                .param("display", "3")
        )
                .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ErrorCode.CD_0000.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.CD_0000.getMessage()))
                .andExpect(jsonPath("$.data").isArray())
        ;
    }

    @Test
    public void 검색_요청파라미터_검증_page() throws Exception {

        // given
        given(keyWordService.getKeyWordHistory(any(), any())).willReturn(pageHistorySuccess());

        // when
        final ResultActions actions = mockMvc.perform(get("/keyWord/history")
                .param("page", "101")
                .param("display", "3")
        )
                .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.code").value(ErrorCode.CD_S001.getCode()))
        ;
    }


    @Test
    public void 검색_요청파라미터_검증_display() throws Exception {

        // given
        given(keyWordService.getKeyWordHistory(any(), any())).willReturn(pageHistorySuccess());

        // when
        final ResultActions actions = mockMvc.perform(get("/keyWord/history")
                .param("page", "1")
                .param("display", "51")
        )
                .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.code").value(ErrorCode.CD_S001.getCode()))
        ;
    }

    public PageResponse<KeyWordHistory> pageHistorySuccess() {
        List<KeyWordHistory> datas = Arrays.asList(new KeyWordHistory(1l, "한국")
                , new KeyWordHistory(1l, "독도")
                , new KeyWordHistory(1l, "고기"));
        return PageResponse.success(3, 3, 1, datas);
    }

    public DataResponse<List<KeyWordCallInfo>> top10Success() {
        List<KeyWordCallInfo> data = Arrays.asList(new KeyWordCallInfo("test3", 3)
                , new KeyWordCallInfo("test2", 2)
                , new KeyWordCallInfo("test1", 1));

        return DataResponse.success(data);
    }
}
