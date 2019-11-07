package com.kys.openapi.thirdparty.naver.search.book;

import com.kys.openapi.thirdparty.naver.code.SearchSort;
import com.kys.openapi.thirdparty.network.QueryString;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

/**
 * 네이버 북 검색
 */
@ToString
@NoArgsConstructor
@Getter
public class NaverBookSearchRequest implements QueryString {

    private String query;

    private Integer display = 10;

    private Integer start = 1;

    private SearchSort sort;

    @Override
    public String toUrl(UriComponentsBuilder builder){

        addParam(builder, "query", query);
        addParam(builder, "display", display);
        addParam(builder, "start", start);
        addParam(builder, "sort", sort);

        return builder.build().toString();
    }

    public NaverBookSearchRequest(String query) {
        this.query = query;
        this.start = 1;
        this.display = 10;
    }

    @Builder
    public NaverBookSearchRequest(String query, Integer display, Integer start, SearchSort sort) {
        this.query = query;
        this.start = Objects.isNull(start) || start == 0 ? 1 : start;
        this.display = Objects.isNull(display) || display == 0 ? 10 : display;
        this.sort = sort;
    }
}
