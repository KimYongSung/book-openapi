package com.kys.openapi.user.controller;

import com.kys.openapi.app.constants.ErrorCode;
import com.kys.openapi.app.result.DataResponse;
import com.kys.openapi.app.result.Response;
import com.kys.openapi.user.dto.TokenInfo;
import com.kys.openapi.user.dto.UserDTO;
import com.kys.openapi.user.service.UserService;
import com.kys.support.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void 필수값_확인() throws Exception {
        // given
        UserDTO userDTO = new UserDTO(null, "123");

        given(userService.joinUser(eq(userDTO))).willReturn(Response.success());

        // when
        final ResultActions actions = mockMvc.perform(post("/user/join")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                                             .content(JsonUtil.objectToJson(userDTO)))
                                             .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().is4xxClientError())
               .andExpect(jsonPath("code").value(ErrorCode.CD_S001.getCode()))
               .andExpect(jsonPath("message").value(ErrorCode.CD_S001.getMessage()));
    }

    @Test
    public void 성공() throws Exception {
        // given
        String param = "kys0213";
        UserDTO userDTO = new UserDTO(param, param);

        given(userService.joinUser(eq(userDTO))).willReturn(Response.success());

        // when
        final ResultActions actions = mockMvc.perform(post("/user/join")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JsonUtil.objectToJson(userDTO)))
                                             .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ErrorCode.CD_0000.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.CD_0000.getMessage()));
    }

    @Test
    public void 로그인() throws Exception {
        // given
        UserDTO userDTO = new UserDTO("kys0213", "kys0213");

        given(userService.loginUser(eq(userDTO))).willReturn(DataResponse.success(TokenInfo.valueOf("testtoken")));

        // when
        final ResultActions actions = mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JsonUtil.objectToJson(userDTO)))
                .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ErrorCode.CD_0000.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.CD_0000.getMessage()))
                .andExpect(jsonPath("$.data.token").isNotEmpty());
    }

    @Test
    public void 로그인_필수값오류() throws Exception {
        // given
        UserDTO userDTO = new UserDTO("", "kys0213");

        given(userService.loginUser(eq(userDTO))).willReturn(DataResponse.success(TokenInfo.valueOf("testtoken")));

        // when
        final ResultActions actions = mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JsonUtil.objectToJson(userDTO)))
                .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.code").value(ErrorCode.CD_S001.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.CD_S001.getMessage()))
        ;
    }
}
