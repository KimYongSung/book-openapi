package com.kys.openapi.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    CD_0000("0000","성공"),
    CD_1001("1001","ID 또는 비밀번호가 잘못되었습니다.")
    ;

    private String code;

    private String message;

}
