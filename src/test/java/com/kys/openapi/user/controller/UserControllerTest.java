package com.kys.openapi.user.controller;

import com.kys.openapi.app.constants.ErrorCode;
import com.kys.openapi.app.result.Response;
import com.kys.openapi.user.dto.UserDTO;
import com.kys.openapi.user.service.UserService;
import com.kys.support.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
@WebMvcTest(UserController.class)
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
        final ResultActions actions = mockMvc.perform(post("/join/user")
                                             .contentType(MediaType.APPLICATION_JSON)
                                             .content(JsonUtil.objectToJson(userDTO)))
                                             .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().isOk())
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
        final ResultActions actions = mockMvc.perform(post("/join/user")
                                             .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                             .param("userId", param)
                                             .param("userPwd", param))
                                             .andDo(MockMvcResultHandlers.print());

        // then
        actions.andExpect(status().isOk())
               .andExpect(jsonPath("code").value(ErrorCode.CD_0000.getCode()))
               .andExpect(jsonPath("message").value(ErrorCode.CD_0000.getMessage()));
    }
}
