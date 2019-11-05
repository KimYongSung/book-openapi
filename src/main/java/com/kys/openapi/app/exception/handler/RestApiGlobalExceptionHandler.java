package com.kys.openapi.app.exception.handler;

import com.kys.openapi.app.constants.ErrorCode;
import com.kys.openapi.app.result.Response;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestAPI Global Exception Handler
 */
@RestController
@ControllerAdvice(annotations = {RestController.class})
public class RestApiGlobalExceptionHandler {

    @ExceptionHandler(value = BindException.class)
    public Response validationError(BindException e){
        return Response.error(ErrorCode.CD_S001);
    }
}
