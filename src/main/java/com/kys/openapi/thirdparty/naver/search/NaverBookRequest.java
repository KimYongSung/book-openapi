package com.kys.openapi.thirdparty.naver.search;

import com.kys.openapi.thirdparty.naver.code.SearchSort;
import com.kys.openapi.thirdparty.network.QueryString;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@ToString
@NoArgsConstructor
@Getter
public class NaverBookRequest implements QueryString {

    private String query;

    private Integer display = 10;

    private Integer start = 1;

    private SearchSort sort;

    @Override
    public String toQueryString() {

        StringBuilder strBuilder = new StringBuilder(query);
        strBuilder.append("query=").append(query)
                  .append("&display=").append(display)
                  .append("&start=").append(start);

        if(Objects.nonNull(sort)){
            strBuilder.append("&sort=").append(sort.name());
        }

        return strBuilder.toString();
    }

    @Builder
    public NaverBookRequest(String query, Integer display, Integer start, SearchSort sort) {
        this.query = query;
        this.display = display;
        this.start = start;
        this.sort = sort;
    }
}
