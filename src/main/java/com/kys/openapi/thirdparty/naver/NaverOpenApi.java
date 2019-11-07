package com.kys.openapi.thirdparty.naver;

import com.kys.openapi.thirdparty.config.ApiUrl;

/**
 * 네이버 openApi end point 관리
 */
public enum NaverOpenApi implements ApiUrl {

    BOOK_SEARCH("/v1/search/book.xml")
    , BOOK_DETAIL("/v1/search/book_adv.xml")
    ;

    private static final String URL = "https://openapi.naver.com";

    private String uri;

    NaverOpenApi(String uri) {
        this.uri = uri;
    }

    @Override
    public String getFullUrl() {
        return URL + this.uri;
    }
}
