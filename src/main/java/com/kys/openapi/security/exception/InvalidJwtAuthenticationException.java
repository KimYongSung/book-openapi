package com.kys.openapi.security.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {

	public InvalidJwtAuthenticationException(String e) {
        super(e);
    }
}
