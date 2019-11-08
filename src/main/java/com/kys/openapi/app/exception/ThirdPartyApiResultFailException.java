package com.kys.openapi.app.exception;

import com.kys.openapi.app.constants.ErrorCode;

public class ThirdPartyApiResultFailException extends OpenApiException {

    public ThirdPartyApiResultFailException() {
        super(ErrorCode.CD_2002);
    }
}