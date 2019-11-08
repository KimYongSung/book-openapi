package com.kys.openapi.app.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kys.openapi.app.constants.ErrorCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString(callSuper = true)
public class ListResponse<T> extends Response{

    private Integer totalSize;

    private Integer size;

    private boolean isEnd;

    @JsonProperty(value = "data")
    private List<T> data;

    protected ListResponse(ErrorCode errorCode, Integer totalSize, Integer size, boolean isEnd, List<T> data) {
        super(errorCode);
        this.totalSize = totalSize;
        this.size = size;
        this.isEnd = isEnd;
        this.data = data;
    }

    public static <T> ListResponse<T> success( Integer totalSize, Integer size, boolean isEnd, List<T> datas){
        return new ListResponse<>(ErrorCode.CD_0000, totalSize, size, isEnd, datas);
    }

}
