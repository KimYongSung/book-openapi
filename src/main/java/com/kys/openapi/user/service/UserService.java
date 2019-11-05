package com.kys.openapi.user.service;

import com.kys.openapi.app.result.Response;
import com.kys.openapi.user.dto.UserDTO;

import javax.transaction.Transactional;

public interface UserService {

    /**
     * 회원 가입 처리
     * @param dto
     * @return
     */
    @Transactional
    Response joinUser(UserDTO dto);
}
