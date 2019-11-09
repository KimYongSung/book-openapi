package com.kys.openapi.app.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kys.openapi.app.constants.ErrorCode;
import com.querydsl.core.QueryResults;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString(callSuper = true)
public class PageResponse<T> extends Response {

    private int totalSize;

    private int size;

    private int page;

    @JsonProperty(value = "data")
    private List<T> data;

    protected PageResponse(ErrorCode errorCode, int totalSize, int size, int page, List<T> data) {
        super(errorCode);
        this.totalSize = totalSize;
        this.size = size;
        this.page = page;
        this.data = data;
    }

    public static <T> PageResponse<T> success(int totalSize, int size, int page, List<T> datas) {
        return new PageResponse<>(ErrorCode.CD_0000, totalSize, size, page, datas);
    }

    public static <T> PageResponse<T> success(QueryResults<T> result) {
        int total = (int) result.getTotal();
        int limit = (int) result.getLimit();
        int offset = (int) result.getOffset();
        List<T> results = result.getResults();
        return new PageResponse<T>(ErrorCode.CD_0000, total, limit, offset, results);
    }

}
