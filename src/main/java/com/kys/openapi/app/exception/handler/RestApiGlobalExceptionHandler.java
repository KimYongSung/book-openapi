package com.kys.openapi.app.exception.handler;

import com.kys.openapi.app.constants.ErrorCode;
import com.kys.openapi.app.exception.OpenApiException;
import com.kys.openapi.app.exception.UserNotFoundException;
import com.kys.openapi.app.result.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

/**
 * RestAPI Global Exception Handler
 */
@RestControllerAdvice
public class RestApiGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = OpenApiException.class)
    @ResponseBody
    public ResponseEntity<Object> handler(OpenApiException e, WebRequest request){
        return handleExceptionInternal(e, e.toResponse(), null, HttpStatus.OK, request);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handler(UserNotFoundException e, WebRequest request) {
        return handleExceptionInternal(e, Response.error(ErrorCode.CD_1001), null, HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseBody
    public ResponseEntity<Object> handler(AuthenticationException e, WebRequest request){
        return handleExceptionInternal(e, Response.error(ErrorCode.CD_S002), null, HttpStatus.UNAUTHORIZED, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        return handleExceptionInternal(ex, Response.error(ErrorCode.CD_S001), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, Response.error(ex.getBindingResult()), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(
            BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, Response.error(ex), headers, status, request);
    }
}
