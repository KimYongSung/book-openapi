package com.kys.openapi.thirdparty.kakao;

import com.kys.openapi.thirdparty.network.ApiUrl;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum KakaoApi implements ApiUrl {

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
