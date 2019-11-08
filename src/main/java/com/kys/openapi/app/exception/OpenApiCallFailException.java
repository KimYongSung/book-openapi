package com.kys.openapi.app.exception;

import com.kys.openapi.app.constants.ErrorCode;

public class OpenApiCallFailException extends OpenApiException {

    public OpenApiCallFailException() {
        super(ErrorCode.CD_2000);
    }
}
