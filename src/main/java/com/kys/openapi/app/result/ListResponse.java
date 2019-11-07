package com.kys.openapi.app.result;

import com.kys.openapi.app.constants.ErrorCode;
import lombok.Getter;

import java.util.List;

@Getter
public class ListResponse<T> extends Response{

    private Integer totalSize;

    private Integer size;

    private boolean isEnd;

    private List<T> datas;

    protected ListResponse(ErrorCode errorCode, Integer totalSize, Integer size, boolean isEnd, List<T> datas) {
        super(errorCode);
        this.totalSize = totalSize;
        this.size = size;
        this.isEnd = isEnd;
        this.datas = datas;
    }

    public static <T> ListResponse<T> success( Integer totalSize, Integer size, boolean isEnd, List<T> datas){
        return new ListResponse<>(ErrorCode.CD_0000, totalSize, size, isEnd, datas);
    }
}
