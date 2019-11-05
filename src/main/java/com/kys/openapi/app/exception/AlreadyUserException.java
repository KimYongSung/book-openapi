package com.kys.openapi.app.exception;

import com.kys.openapi.app.constants.ErrorCode;

/**
 * 기가입 회원
 */
public class AlreadyUserException extends OpenApiException {

    public AlreadyUserException() {
        super(ErrorCode.CD_1002);
    }
}
