package com.kys.openapi.thirdparty.naver.common;

import lombok.Getter;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "result")
@Getter
public class ErrorResponse {

    private String errorMessage;

    private String errorCode;
}
