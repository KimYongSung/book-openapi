package com.kys.openapi.app.result;

import com.kys.openapi.app.constants.ErrorCode;
import lombok.Getter;
import lombok.ToString;

/**
 * API 응답 정보
 */
@Getter
@ToString
public class Response {

    private String code;

    private String message;

    protected Response(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    /**
     * API 실패 응답 생성
     * @param errorCode 결과코드
     * @return
     */
    public static Response error(ErrorCode errorCode){
        return new Response(errorCode);
    }

    /**
     * API 성공 응답 생성
     * @return
     */
    public static Response success(){
        return new Response(ErrorCode.CD_0000);
    }

}
