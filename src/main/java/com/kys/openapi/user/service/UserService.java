package com.kys.openapi.user.service;

import com.kys.openapi.app.result.Response;
import com.kys.openapi.user.dto.UserDTO;

public interface UserService {

    /**
     * 회원 가입 처리
     * @param dto
     * @return
     */
    Response joinUser(UserDTO dto);

    /**
     * 로그인
     * @param dto
     * @return
     */
    Response loginUser(UserDTO dto);
}
