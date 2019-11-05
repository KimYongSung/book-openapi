package com.kys.openapi.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestAPI Global Exception Handler
 */
@ControllerAdvice(annotations = {RestController.class})
public class RestApiGlobalExceptionHandler {


}
