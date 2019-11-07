package com.kys.openapi.app.exception;

import com.kys.openapi.app.constants.ErrorCode;
import org.springframework.security.core.AuthenticationException;

/**
 * 고객 정보가 없을 경우 발생
 */
public class UserNotFoundException extends AuthenticationException {

    public UserNotFoundException() {
        super(ErrorCode.CD_1001.getMessage());
    }
}
