package com.kys.openapi.app.exception;

import com.kys.openapi.app.constants.ErrorCode;

public class ThirdPartyApiCallFailException extends OpenApiException {

    public ThirdPartyApiCallFailException() {
        super(ErrorCode.CD_2000);
    }
}
