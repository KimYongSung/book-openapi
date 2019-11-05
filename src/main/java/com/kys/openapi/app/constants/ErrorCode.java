package com.kys.openapi.app.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 에러코드 관리
 */
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    CD_0000("0000","성공")
    , CD_S001("S001", "요청 파라미터가 유효하지 않습니다")
    , CD_1001("1001","ID 또는 비밀번호가 잘못되었습니다.")
    , CD_1002("1002","이미 가입한 회원정보 입니다.")
    ;

    private String code;

    private String message;

}
