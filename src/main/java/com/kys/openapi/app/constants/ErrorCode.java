package com.kys.openapi.app.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 에러코드 관리
 */
@Getter
@ToString
@AllArgsConstructor
public enum ErrorCode {

    CD_0000("0000","성공")
    , CD_S001("S001", "요청 파라미터가 유효하지 않습니다")
    , CD_S002("S002", "인증 정보가 잘못되었습니다.")
    , CD_S999("S999", "system error")
    , CD_1001("1001","고객정보를 찾을 수 없습니다.")
    , CD_1002("1002","이미 가입한 회원정보 입니다.")
    , CD_2000("2000","API 요청을 실패하였습니다.")
    , CD_2001("2001","API 요청 결과가 없습니다.")
    , CD_2002("2002","API 요청 실패하였습니다.")
    ;

    private String code;

    private String message;

}
