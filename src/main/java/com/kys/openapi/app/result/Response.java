package com.kys.openapi.app.result;

import com.kys.openapi.app.constants.ErrorCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.BindingResult;

import java.util.Objects;


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

    private Response(String validMsg) {
        this.code = ErrorCode.CD_S001.getCode();
        this.message = validMsg;
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
     * 파라미터 유효성 실패 에러
     *
     * @param result
     * @return
     */
    public static Response validationError(BindingResult result) {
        return Objects.isNull(result) ? new Response(ErrorCode.CD_S001) : new Response(result.getFieldError().getDefaultMessage());
    }
    /**
     * API 성공 응답 생성
     * @return
     */
    public static Response success(){
        return new Response(ErrorCode.CD_0000);
    }

}
