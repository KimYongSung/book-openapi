package com.kys.openapi.app.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kys.openapi.app.constants.ErrorCode;
import lombok.ToString;

/**
 * API 응답 정보
 * @param <T>
 */
@ToString(callSuper = true)
public class DataResponse<T> extends Response {

    @JsonProperty(value = "data")
    private T data;

    protected DataResponse(ErrorCode errorCode, T data) {
        super(errorCode);
        this.data = data;
    }

    /**
     * API 응답 생성
     * @param data      응답 데이터
     * @return
     */
    public static <T> DataResponse<T> success(T data){
        return new DataResponse<>(ErrorCode.CD_0000, data);
    }
}
