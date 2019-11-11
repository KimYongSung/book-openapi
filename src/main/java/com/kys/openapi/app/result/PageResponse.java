package com.kys.openapi.app.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kys.openapi.app.constants.ErrorCode;
import com.querydsl.core.QueryResults;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;

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

    public static <T> PageResponse<T> success(int totalSize, int size, int page, List<T> data) {
        return new PageResponse<>(ErrorCode.CD_0000, totalSize, size, page, data);
    }

    public static <T> PageResponse<T> success(QueryResults<T> result, PageRequest pageRequest) {
        List<T> results = result.getResults();
        int limit = results.size();
        int total = (int) result.getTotal();
        /*int offset = (int) Math.floor((double) limit / (double) pageRequest.getPageSize());*/
        int offset = (int) pageRequest.getPageNumber() + 1;
        return new PageResponse<T>(ErrorCode.CD_0000, total, limit, offset, results);
    }

}
