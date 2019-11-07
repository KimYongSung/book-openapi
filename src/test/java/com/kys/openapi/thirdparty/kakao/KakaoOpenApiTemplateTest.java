package com.kys.openapi.thirdparty.kakao;

import com.kys.openapi.thirdparty.config.NetWorkConfig;
import com.kys.openapi.thirdparty.config.RestTemplateConfig;
import com.kys.openapi.thirdparty.kakao.code.SearchSort;
import com.kys.openapi.thirdparty.kakao.search.book.KakaoBookSearchDocument;
import com.kys.openapi.thirdparty.kakao.search.book.KakaoBookSearchRequest;
import com.kys.openapi.thirdparty.kakao.search.book.KakaoBookSearchResponse;
import com.kys.openapi.thirdparty.kakao.search.common.SearchMeta;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KakaoOpenApiTemplateTest {

    public static KakaoOpenApiTemplate kakaoOpenApiTemplate;

    @BeforeClass
    public static void setUp(){

        KakaoOpenApiKey kakaoApiKey = new KakaoOpenApiKey("355455c96ccc2c1b228bb552f56d5b44","KakaoAK");
        kakaoApiKey.afterPropertiesSet();
        RestTemplateConfig restTemplateConfig = new RestTemplateConfig(new NetWorkConfig(3000, 3000));
        kakaoOpenApiTemplate = new KakaoOpenApiTemplate(restTemplateConfig.restTemplateBuilder(), kakaoApiKey);
    }

    @Test(expected = RestClientException.class)
    public void 잘못된_인증키_요청(){

        // given
        KakaoOpenApiKey kakaoApiKey = new KakaoOpenApiKey("TEST55c96ccc2c1b228bb552f56d5b44","KakaoAK");
        RestTemplateConfig restTemplateConfig = new RestTemplateConfig(new NetWorkConfig(3000, 3000));
        KakaoOpenApiTemplate kakaoOpenApiTemplate = new KakaoOpenApiTemplate(restTemplateConfig.restTemplateBuilder(), kakaoApiKey);

        KakaoBookSearchRequest request = new KakaoBookSearchRequest("상실의시대");

        // when
        ResponseEntity<KakaoBookSearchResponse> response = kakaoOpenApiTemplate.bookSearch(request);

    }

    @Test
    public void 책정보_조회_테스트(){

        // given
        KakaoBookSearchRequest request = new KakaoBookSearchRequest("상실의시대");

        // when
        ResponseEntity<KakaoBookSearchResponse> response = kakaoOpenApiTemplate.bookSearch(request);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        KakaoBookSearchResponse body            = response.getBody();
        SearchMeta meta                         = body.getMeta();
        List<KakaoBookSearchDocument> documents = body.getDocuments();

        assertThat(meta.getPageableCount()).isEqualTo(documents.size());
    }

    @Test
    public void 책정보_조회_테스트_정렬(){

        // given
        KakaoBookSearchRequest request = KakaoBookSearchRequest.builder()
                                                               .query("상실의 시대")
                                                               .sort(SearchSort.recency)
                                                               .build();

        // when
        ResponseEntity<KakaoBookSearchResponse> response = kakaoOpenApiTemplate.bookSearch(request);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        KakaoBookSearchResponse body            = response.getBody();
        SearchMeta meta                         = body.getMeta();
        List<KakaoBookSearchDocument> documents = body.getDocuments();

        assertThat(meta.getPageableCount()).isEqualTo(documents.size());
    }
}
