package com.kys.openapi.thirdparty.kakao;

import com.kys.openapi.thirdparty.kakao.search.book.KakaoBookSearchRequest;
import com.kys.openapi.thirdparty.kakao.search.book.KakaoBookSearchResponse;
import com.kys.openapi.thirdparty.network.OpenApiRestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 카카오 Open API 송수신 처리자
 */
@Slf4j
@Component
public class KakaoOpenApiRestTemplate extends OpenApiRestTemplate {

    private KakaoOpenApiKey kakaoApiKey;

    public KakaoOpenApiRestTemplate(RestTemplateBuilder builder, KakaoOpenApiKey kakaoApiKey) {
        super(builder);
        this.kakaoApiKey = kakaoApiKey;
    }

    @Override
    protected RestTemplate build(RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * Header 생성
     * @return
     */
    @Override
    public HttpHeaders makeHeaders(){
        return kakaoApiKey.makeHeaders();
    }

    /**
     * 책 검색
     * @param request
     * @return
     */
    public ResponseEntity<KakaoBookSearchResponse> bookSearch(KakaoBookSearchRequest request){
        return get(KakaoOpenApiUrl.BOOK_SEARCH, request, KakaoBookSearchResponse.class);
    }

}
