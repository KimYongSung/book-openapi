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
@AutoConfigureMockMvc
@SpringBootTest
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
               .andExpect(jsonPath("$.data[0].keyWord").hasJsonPath())
               .andExpect(jsonPath("$.data[0].callCount").hasJsonPath())
        ;
    }

    @Test
    public void 검색이력조회() throws Exception {

        // given
        given(keyWordService.getKeyWordHistory(any(), any())).willReturn(pageHistorySuccess());

        // when
        final ResultActions actions = mockMvc.perform(get("/keyWord/history")
                                             .param("start", "1")
                                             .param("length", "3"))
                                             .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().isOk())
               .andExpect(jsonPath("$.code").value(ErrorCode.CD_0000.getCode()))
               .andExpect(jsonPath("$.message").value(ErrorCode.CD_0000.getMessage()))
               .andExpect(jsonPath("$.data").isArray())
               .andExpect(jsonPath("$.data[0].keyWordNo").hasJsonPath())
               .andExpect(jsonPath("$.data[0].userNo").hasJsonPath())
               .andExpect(jsonPath("$.data[0].keyWord").hasJsonPath())
               .andExpect(jsonPath("$.data[0].searchDt").hasJsonPath())
        ;
    }

    @Test
    public void 검색_파라미터_검증_start() throws Exception {

        // given
        given(keyWordService.getKeyWordHistory(any(), any())).willReturn(pageHistorySuccess());

        // when
        final ResultActions actions = mockMvc.perform(get("/keyWord/history")
                                             .param("start", "101")
                                             .param("length", "3"))
                                             .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().is4xxClientError())
               .andExpect(jsonPath("$.code").value(ErrorCode.CD_S001.getCode()))
               .andExpect(jsonPath("$.message").value("start는 1 ~ 100 범위로 요청 가능합니다."))
        ;
    }


    @Test
    public void 검색_파라미터_검증_length() throws Exception {

        // given
        given(keyWordService.getKeyWordHistory(any(), any())).willReturn(pageHistorySuccess());

        // when
        final ResultActions actions = mockMvc.perform(get("/keyWord/history")
                                             .param("start", "1")
                                             .param("length", "51"))
                                             .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().is4xxClientError())
               .andExpect(jsonPath("$.code").value(ErrorCode.CD_S001.getCode()))
               .andExpect(jsonPath("$.message").value("length는 1 ~ 50 범위로 요청 가능합니다."))
        ;
    }

    public PageResponse<KeyWordHistory> pageHistorySuccess() {
        List<KeyWordHistory> data = Arrays.asList(new KeyWordHistory(1l, "한국")
                                                 , new KeyWordHistory(1l, "독도")
                                                 , new KeyWordHistory(1l, "고기"));
        return PageResponse.success(data.size(), data.size(), 1, data);
    }

    public DataResponse<List<KeyWordCallInfo>> top10Success() {
        List<KeyWordCallInfo> data = Arrays.asList(new KeyWordCallInfo("test3", 3)
                                                 , new KeyWordCallInfo("test2", 2)
                                                 , new KeyWordCallInfo("test1", 1));
        return DataResponse.success(data);
    }
}
