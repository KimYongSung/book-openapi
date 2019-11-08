package com.kys.openapi.app.exception;

import com.kys.openapi.app.constants.ErrorCode;
import com.kys.openapi.app.result.Response;
import lombok.Getter;
import lombok.ToString;

/**
 * Open API 공통 Exception
 */
@ToString
public class OpenApiException extends RuntimeException {

    @Getter
    private ErrorCode errorCode;

    public OpenApiException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public OpenApiException(ErrorCode errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public Response toResponse(){
        return Response.error(errorCode);
    }

}
