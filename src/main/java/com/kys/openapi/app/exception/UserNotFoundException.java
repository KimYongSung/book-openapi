package com.kys.openapi.app.exception;

import com.kys.openapi.app.constants.ErrorCode;

/**
 * 고객 정보가 없을 경우 발생
 */
public class UserNotFoundException extends OpenApiException {

    public UserNotFoundException() {
        super(ErrorCode.CD_1001);
    }
}
