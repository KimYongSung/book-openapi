package com.kys.openapi.app.result;

import com.kys.openapi.app.constants.ErrorCode;

/**
 * API 응답 정보
 * @param <T>
 */
public class DataResponse<T> extends Response {

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
