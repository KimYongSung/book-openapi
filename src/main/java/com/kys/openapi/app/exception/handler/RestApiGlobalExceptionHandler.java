package com.kys.openapi.app.exception.handler;

import com.kys.openapi.app.constants.ErrorCode;
import com.kys.openapi.app.result.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * RestAPI Global Exception Handler
 */
@RestControllerAdvice
public class RestApiGlobalExceptionHandler {

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ResponseEntity<Response> handler(BindException e){
        return ResponseEntity.badRequest()
                             .body(Response.error(ErrorCode.CD_S001));
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<Response> handler(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(Response.error(ErrorCode.CD_S999));
    }

    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseBody
    public ResponseEntity<Response> handler(AuthenticationException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body(Response.error(ErrorCode.CD_S002));
    }

}
