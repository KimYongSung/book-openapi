package com.kys.openapi.thirdparty.kakao.search.book;

import com.kys.openapi.thirdparty.config.QueryString;
import com.kys.openapi.thirdparty.kakao.code.BookTarget;
import com.kys.openapi.thirdparty.kakao.code.SearchSort;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor
public class KakaoBookSearchRequest implements QueryString {

    private String query;

    private SearchSort sort;

    private Integer page;

    private Integer size;

    private BookTarget target;

    public KakaoBookSearchRequest(String query){
        this.query = query;
        this.page = 1;
        this.size = 10;
    }

    @Builder
    public KakaoBookSearchRequest(String query, SearchSort sort, Integer page, Integer size, BookTarget target) {
        this.query = query;
        this.sort = sort;
        this.page = Objects.isNull(page) || page == 0 ? 1 : page;
        this.size = Objects.isNull(size) || size == 0 ? 10 : size;
        this.target = target;
    }

    @Override
    public String toUrl(UriComponentsBuilder builder){
        addParam(builder, "query", query);
        addParam(builder, "page", page);
        addParam(builder, "size", size);
        addParam(builder, "sort", sort);
        addParam(builder, "target", target);
        return builder.toUriString();
    }

}
