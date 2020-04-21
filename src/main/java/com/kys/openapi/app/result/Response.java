package com.kys.openapi.app.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kys.openapi.app.constants.ErrorCode;
import com.querydsl.core.QueryResults;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter
@ToString
public class Response {

    private final String code;

    private final String message;

    protected Response(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    private Response(String validMsg) {
        this.code = ErrorCode.CD_S001.getCode();
        this.message = validMsg;
    }

    public static Response systemError(){
        return new Response(ErrorCode.CD_S999);
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
    public static Response error(BindingResult result) {
        return Objects.isNull(result) ? new Response(ErrorCode.CD_S001) : new Response(result.getFieldError().getDefaultMessage());
    }
    /**
     * API 성공 응답 생성
     * @return
     */
    public static Response success(){
        return new Response(ErrorCode.CD_0000);
    }

    /**
     * API 응답 생성
     * @param data      응답 데이터
     * @return
     */
    public static <T> Response success(T data){
        return new DataResponse<T>(ErrorCode.CD_0000, data);
    }

    /**
     * 페이지 응답 생성
     * @param totalSize 총 항목 값
     * @param pageable 페이지 요청 정보
     * @param data 실제 리스트 정보
     * @return
     */
    public static <T> Response success(int totalSize, Pageable pageable, List<T> data) {
        return new PageResponse<T>(ErrorCode.CD_0000, totalSize, pageable.getPageSize(), pageable.getPageNumber(), data);
    }

    /**
     * 페이지 응답 생성
     * @param totalSize 총 항목 값
     * @param data 실제 리스트 정보
     * @return
     */
    public static <T> Response success(int totalSize, int size, int page, List<T> data) {
        return new PageResponse<T>(ErrorCode.CD_0000, totalSize, size, page, data);
    }

    /**
     *
     * @param pageable
     * @param <T>
     * @return
     */
    public static <T> Response emptyPage(Pageable pageable) {
        return success(0, 0, pageable.getPageNumber(), Collections.emptyList());
    }

    /**
     * 공백 리스트
     * @param page
     * @param <T>
     * @return
     */
    public static <T> Response emptyPage(int page) {
        return success(0, 0, page, Collections.emptyList());
    }

    /**
     * 페이지 응답 생성
     * @param result 페이징 쿼리 결과
     * @param pageable 페이지 요청 정보
     * @return
     */
    public static <T> Response success(QueryResults<T> result, Pageable pageable) {
        List<T> results = result.getResults();
        int limit = results.size();
        int total = (int) result.getTotal();
        int offset = pageable.getPageNumber() + 1;
        return new PageResponse<T>(ErrorCode.CD_0000, total, limit, offset, results);
    }

    /**
     * 페이지 응답 생성
     * @param result 페이징 쿼리 결과
     * @param pageable 페이지 요청 정보
     * @return
     */
    public static <T> Response success(Page<T> result, Pageable pageable) {
        List<T> results = result.getContent();
        int size = results.size();
        int totalSize = (int) result.getTotalElements();
        int page = pageable.getPageNumber() + 1;
        return new PageResponse<T>(ErrorCode.CD_0000, totalSize, size, page, results);
    }

    /**
     * API 응답 정보
     * @param <T>
     */
    @ToString(callSuper = true)
    @Getter
    private static final class DataResponse<T> extends Response {

        @JsonProperty(value = "data")
        private final T data;

        private DataResponse(ErrorCode errorCode, T data) {
            super(errorCode);
            this.data = data;
        }

    }

    /**
     * Page 응답 정보
     * @param <T>
     */
    @Getter
    @ToString(callSuper = true)
    private static final class PageResponse<T> extends Response {

        private final int totalSize;

        private final int size;

        private final int page;

        @JsonProperty(value = "data")
        private final List<T> data;

        private PageResponse(ErrorCode errorCode, int totalSize, int size, int page, List<T> data) {
            super(errorCode);
            this.totalSize = totalSize;
            this.size = size;
            this.page = page;
            this.data = data;
        }
    }
}
