package com.kys.openapi.thirdparty.kakao;

import com.kys.openapi.thirdparty.ApiUrl;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 카카오 API Endpoint 관리
 */
@AllArgsConstructor
public enum KakaoOpenApiUrl implements ApiUrl {

    /**
     * 책 검색
     */
    BOOK_SEARCH("/v3/search/book")
    ;

    private static final String URL = "https://dapi.kakao.com";

    @Getter
    private String uri;

    @Override
    public String getFullUrl(){
        return URL + this.uri;
    }
}
