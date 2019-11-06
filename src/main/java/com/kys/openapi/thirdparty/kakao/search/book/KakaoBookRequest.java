package com.kys.openapi.thirdparty.kakao.search.book;

import com.kys.openapi.thirdparty.kakao.code.BookTarget;
import com.kys.openapi.thirdparty.kakao.code.SearchSort;
import com.kys.openapi.thirdparty.network.QueryString;
import lombok.*;

import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor
public class KakaoBookRequest implements QueryString {

    private String query;

    private SearchSort sort;

    private Integer page = 1;

    private Integer size = 10;

    private BookTarget target;

    public KakaoBookRequest(String query){
        this.query = query;
    }

    @Builder
    public KakaoBookRequest(String query, SearchSort sort, Integer page, Integer size, BookTarget target) {
        this.query = query;
        this.sort = sort;
        this.page = page;
        this.size = size;
        this.target = target;
    }

    @Override
    public String toQueryString(){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("query=").append(query)
                     .append("&page=").append(page)
                     .append("&size=").append(size);

        if(Objects.nonNull(sort)){
            stringBuilder.append("&sort=").append(sort.name());
        }

        if(Objects.nonNull(target)){
            stringBuilder.append("&target=").append(target.name());
        }

        return stringBuilder.toString();
    }

}
