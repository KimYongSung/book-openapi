package com.kys.openapi.thirdparty.naver;

import com.kys.openapi.thirdparty.naver.search.book.NaverBookDetailSearchRequest;
import com.kys.openapi.thirdparty.naver.search.book.NaverBookSearchRequest;
import com.kys.openapi.thirdparty.naver.search.book.NaverBookSearchResponse;
import com.kys.openapi.thirdparty.network.OpenApiRestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 네이버 Open API 송수신 처리자
 */
@Slf4j
@Component
public class NaverOpenApiRestTemplate extends OpenApiRestTemplate {

    private NaverOpenApiKey naverApiKey;

    public NaverOpenApiRestTemplate(RestTemplateBuilder builder, NaverOpenApiKey naverApiKey) {
        super(builder);
        this.naverApiKey = naverApiKey;
    }

    @Override
    protected RestTemplate build(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Override
    public HttpHeaders makeHeaders() {
        return naverApiKey.makeHeader();
    }

    /**
     * 책 검색
     * @param request 요청 파라미터
     * @return
     */
    public ResponseEntity<NaverBookSearchResponse> bookSearch(NaverBookSearchRequest request){
        return get(NaverOpenApiUrl.BOOK_SEARCH, request, NaverBookSearchResponse.class);
    }

    /**
     * 책 상세 검색
     * @param request 요청 파라미터
     * @return
     */
    public ResponseEntity<NaverBookSearchResponse> bookDetailSearch(NaverBookDetailSearchRequest request){
        return get(NaverOpenApiUrl.BOOK_DETAIL, request, NaverBookSearchResponse.class);
    }
    
}
