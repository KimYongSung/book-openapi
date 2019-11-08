package com.kys.openapi.app.exception;

import com.kys.openapi.app.constants.ErrorCode;

public class ThirdPartyApiResultNotFoundException extends OpenApiException {

    public ThirdPartyApiResultNotFoundException() {
        super(ErrorCode.CD_2001);
    }
}
