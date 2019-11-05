package com.kys.openapi.exception.user;

import com.kys.openapi.exception.ErrorCode;
import com.kys.openapi.exception.OpenApiException;

/**
 * 고객 정보가 없을 경우 발생
 */
public class UserNotFoundException extends OpenApiException {

    public UserNotFoundException() {
        super(ErrorCode.CD_1001);
    }
}
